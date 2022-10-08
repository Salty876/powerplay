package org.firstinspires.ftc.teamcode.src

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.src.models.BotHardware

@TeleOp(name = "TeleOp Mode", group = "OpMode")
class BotOpMode: LinearOpMode() {
    private var bot: BotHardware? = null
    override fun runOpMode() {
        bot = BotHardware(this, hardwareMap, gamepad1, telemetry)
        telemetry.addData("Status:", "Initialised")

        waitForStart()
        while(opModeIsActive()) {
            telemetry.addData("Status:", "Is Running")
            val left: Double? = bot!!.forward?.plus(bot?.pivot!!)
            val right: Double? = bot!!.forward?.minus(bot?.pivot!!)
            val leftPower = right?.let { Range.clip(it, -1.0, 1.0) }
            val rightPower = left?.let { Range.clip(it, -1.0, 1.0) }

            if (leftPower != null || rightPower != null) {
                if (leftPower != null) {
                    bot!!.fl.power = leftPower
                    bot!!.bl.power = leftPower
                }
                if (rightPower != null) {
                    bot!!.fr.power = rightPower
                    bot!!.br.power = rightPower
                }
            }
            telemetry.update()
        }
    }
}

