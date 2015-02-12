package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.commands.TankDriveWithJoysticks;
import org.usfirst.frc.team294.robot.commands.autoMode.TrajectoryDriveController;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;
import org.usfirst.frc.team294.robot.util.RateLimitFilter;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Drivetrain extends Subsystem {

	int[] leftDrive={RobotMap.leftMotor1,RobotMap.leftMotor2};
	SpeedController leftMotor = new MultiCANTalon(leftDrive);

	int[] rightDrive={RobotMap.rightMotor1,RobotMap.rightMotor2};
	SpeedController rightMotor = new MultiCANTalon(rightDrive);

	RobotDrive drive = new RobotDrive(leftMotor, rightMotor);

	Encoder rightDriveEncoder = new Encoder(RobotMap.kDIN_rightDriveEncoderA,
			RobotMap.kDIN_rightDriveEncoderB);
	Encoder leftDriveEncoder = new Encoder(RobotMap.kDIN_leftDriveEncoderA,
			RobotMap.kDIN_leftDriveEncoderB);

	RateLimitFilter leftFilter = new RateLimitFilter(6.0);
	RateLimitFilter rightFilter = new RateLimitFilter(6.0);

	// ticks to feet
	public final double RIGHT_ENCOCDER_TO_DISTANCE_RATIO = (3.5 * Math.PI) / (12.0 * 256.0);
	public final double LEFT_ENCOCDER_TO_DISTANCE_RATIO = (3.5 * Math.PI) / (12.0 * 256.0);

	Timer lowBatteryTimer = new Timer();
	Timer lowBatteryScaleTimer = new Timer();
	double lowBatteryScale = 1.0;

	SerialPort serial_port;
	//IMU imu;  // Alternatively, use IMUAdvanced for advanced features
	IMUAdvanced imu;

	public Drivetrain() {
		lowBatteryTimer.start();
		lowBatteryScaleTimer.start();
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);


		try {
			serial_port = new SerialPort(57600,SerialPort.Port.kMXP);

			// You can add a second parameter to modify the 
			// update rate (in hz) from 4 to 100.  The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			//byte update_rate_hz = 50;
			//imu = new IMU(serial_port,update_rate_hz);
			imu = new IMUAdvanced(serial_port);
		} catch( Exception ex ) {
			throw ex;
		}
		if ( imu != null ) {
			LiveWindow.addSensor("IMU", "Gyro", imu);
		}
	}

	public float getYaw(){
		//System.out.println(this.imu.getYaw());
		return this.imu.getYaw();

	}


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TankDriveWithJoysticks());
	}

	public void stop() {
		drive.tankDrive(0, 0);
		lowBatteryScale = 1.0;
	}

	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}

	public void autoDrive(double speed) {
		double leftValue = leftDriveEncoder.get();
		double rightValue = rightDriveEncoder.get();

		double leftSpeed = speed;
		double rightSpeed = speed;

		if (leftValue > rightValue) {
			leftSpeed -= (leftValue - rightValue) / 100.0;
			if (speed >= 0 && leftSpeed < 0)
				leftSpeed = 0.0;
		} else if (leftValue < rightValue) {
			rightSpeed -= (rightValue - leftValue) / 100.0;
			if (speed >= 0 && rightSpeed < 0)
				rightSpeed = 0.0;
		}

		// logging.info("lenc: %s renc: %s lout: %s rout: %s", leftValue,
		// rightValue, leftSpeed, rightSpeed)
		tankDrive(leftSpeed, rightSpeed);
	}

	public void tankDrive(double lPower, double rPower) {
		double[] motorPower=this.scaleMotorPowerToPreventBurnout(lPower, rPower);
		lPower=motorPower[0];
		rPower=motorPower[1];
		double l = leftFilter.update(lPower);
		double r = rightFilter.update(rPower);
		// monitor battery voltage; if less than 6V for X time, reduce
		// drivetrain by Y factor for a period of time
		// System.out.println("voltage: " + Robot.pdp.getVoltage());
		/*
		 * if (Robot.pdp.getVoltage() > 6.5) lowBatteryTimer.reset(); if
		 * (lowBatteryTimer.get() > 0.1) { lowBatteryScaleTimer.reset();
		 * lowBatteryScale = 0.25; } if (lowBatteryScaleTimer.get() > 0.5)
		 */
		lowBatteryScale = 1.0;
		// System.out.println("l: " + l + " r: " + r + " lbs: " +
		// lowBatteryScale);
		drive.tankDrive(l * lowBatteryScale, r * lowBatteryScale, false);
	}

	//to prevent burnout and overspeed in auto mode
	public double[] scaleMotorPowerToPreventBurnout(double lPower, double rPower){
		double absLPower=Math.abs(lPower);
		double absRPower=Math.abs(rPower);
		if(absRPower>1 || absLPower>1){
			System.out.println("Scaling down tank drive command power to prevent burnout.\n"
					+ "One or more absolute values sent to tank drive was greater than 1.\n"
					+ "Recieved lPower: "+lPower+" \nRecieved rPower: "+rPower);
			if(absRPower>=1){
				rPower/=absRPower;
				lPower/=absRPower;
			}else{
				rPower/=absLPower;
				lPower/=absLPower;
			}
			System.out.println("New lPower: "+lPower+" \nNew rPower: "+rPower);
		}
		double[] finalVals=new double[]{lPower,rPower};
		return finalVals;
	}

	public void arcDrive(double lPower, double rPower) {
		double l = leftFilter.update(lPower);
		double r = rightFilter.update(rPower);
		// System.out.println("voltage: " + Robot.pdp.getVoltage());
		/*
		 * if (Robot.pdp.getVoltage() > 6.5) lowBatteryTimer.reset(); if
		 * (lowBatteryTimer.get() > 0.1) { lowBatteryScaleTimer.reset();
		 * lowBatteryScale = 0.25; } if (lowBatteryScaleTimer.get() > 0.5)
		 */
		lowBatteryScale = 1.0;
		drive.arcadeDrive(l * lowBatteryScale, r * lowBatteryScale, false);
	}

	public double getLeft() {
		leftDriveEncoder.setDistancePerPulse(.1);
		double left = leftDriveEncoder.getDistance();
		return left;
	}

	public double getRight() {
		rightDriveEncoder.setDistancePerPulse(.1);
		double right = rightDriveEncoder.getDistance();
		return right;
	}

	public double getLeftEncoderDistance() { // in feet
		return leftDriveEncoder.get() * LEFT_ENCOCDER_TO_DISTANCE_RATIO;
	}

	public double getRightEncoderDistance(){
		return rightDriveEncoder.get() * LEFT_ENCOCDER_TO_DISTANCE_RATIO;
	}

	public double getGyroAngleInRadians() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void useController(TrajectoryDriveController driveController) {
		// TODO Auto-generated method stub

	}

}
