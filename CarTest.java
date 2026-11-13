public class CarTest {

    private static void reportResult(String testName, boolean success) {
        System.out.println((success ? "SUCCESS: " : "FAILURE: ") + testName);
    }

    private static void testGasIncreasesSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double oldSpeed = saab.getCurrentSpeed();
        saab.gas(1);
        double newSpeed = saab.getCurrentSpeed();

        reportResult("Gas increases speed", newSpeed > oldSpeed);
    }

    private static void testBrakeDecreasesSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(0.5);

        double oldSpeed = saab.getCurrentSpeed();
        saab.brake(0.5);
        double newSpeed = saab.getCurrentSpeed();

        reportResult("Brake decreases speed", newSpeed < oldSpeed);
    }

    private static void testGasAcceptsBoundaryValues() {
        Saab95 saab = new Saab95();
        boolean bothAccepted = true;

        try {
            saab.gas(0);
            saab.gas(1);
        } catch (IllegalArgumentException e) {
            bothAccepted = false;
        }

        reportResult("Gas accepts 0 and 1", bothAccepted);
    }

    private static void testBrakeAcceptsBoundaryValues() {
        Saab95 saab = new Saab95();
        boolean bothAccepted = true;

        try {
            saab.brake(0);
            saab.brake(1);
        } catch (IllegalArgumentException e) {
            bothAccepted = false;
        }

        reportResult("Brake accepts 0 and 1", bothAccepted);
    }

    private static void testGasRejectsAmountAboveOne() {
        Saab95 saab = new Saab95();
        boolean rejected = false;

        try {
            saab.gas(1.1);
        } catch (IllegalArgumentException e) {
            rejected = true;
        }

        reportResult("Gas rejects amount above 1", rejected);
    }

    private static void testGasRejectsNegativeAmount() {
        Saab95 saab = new Saab95();
        boolean rejected = false;

        try {
            saab.gas(-0.1);
        } catch (IllegalArgumentException e) {
            rejected = true;
        }

        reportResult("Gas rejects negative amount", rejected);
    }

    private static void testBrakeRejectsAmountAboveOne() {
        Saab95 saab = new Saab95();
        boolean rejected = false;

        try {
            saab.brake(1.1);
        } catch (IllegalArgumentException e) {
            rejected = true;
        }

        reportResult("Brake rejects amount above 1", rejected);
    }

    private static void testBrakeRejectsNegativeAmount() {
        Saab95 saab = new Saab95();
        boolean rejected = false;

        try {
            saab.brake(-0.1);
        } catch (IllegalArgumentException e) {
            rejected = true;
        }

        reportResult("Brake rejects negative amount", rejected);
    }

    private static void testGasCannotDecreaseSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double oldSpeed = saab.getCurrentSpeed();
        saab.gas(0.5);

        reportResult("Gas cannot decrease speed", saab.getCurrentSpeed() >= oldSpeed);
    }

    private static void testBrakeCannotIncreaseSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(0.5);

        double oldSpeed = saab.getCurrentSpeed();
        saab.brake(0.5);

        reportResult("Brake cannot increase speed", saab.getCurrentSpeed() <= oldSpeed);
    }

    private static void testSpeedNeverExceedsEnginePower() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        for (int i = 0; i < 100; i++) {
            saab.gas(1);
        }

        reportResult(
            "Speed never exceeds engine power",
            saab.getCurrentSpeed() <= saab.getEnginePower()
        );
    }

    private static void testSpeedNeverBecomesNegative() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        for (int i = 0; i < 100; i++) {
            saab.brake(1);
        }

        reportResult("Speed never becomes negative", saab.getCurrentSpeed() >= 0);
    }

    private static void testVolvoGasIncreasesSpeed() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();

        double oldSpeed = volvo.getCurrentSpeed();
        volvo.gas(0.5);

        reportResult("Volvo gas increases speed", volvo.getCurrentSpeed() > oldSpeed);
    }

    private static void testTurbo() {
        Saab95 slowSaab = new Saab95();
        Saab95 fastSaab = new Saab95();

        slowSaab.setTurboOff();
        fastSaab.setTurboOn();

        slowSaab.startEngine();
        fastSaab.startEngine();

        slowSaab.gas(0.5);
        fastSaab.gas(0.5);

        reportResult(
            "Turbo yields more speed",
            slowSaab.getCurrentSpeed() < fastSaab.getCurrentSpeed()
        );
    }

    private static void testForwardMoveIncreasesY() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double oldY = saab.getY();
        saab.move();

        reportResult("Forward move increases y", saab.getY() > oldY);
    }

    private static void testRightMoveIncreasesX() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.turnRight();

        double oldX = saab.getX();
        saab.move();

        reportResult("Right move increases x", saab.getX() > oldX);
    }

    private static void testTurnLeftChangesDirection() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double oldX = saab.getX();
        saab.turnLeft();
        saab.move();

        reportResult("Left turn makes move decrease x", saab.getX() < oldX);
    }

    private static void testTurnRightChangesDirection() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double oldX = saab.getX();
        saab.turnRight();
        saab.move();

        reportResult("Right turn makes move increase x", saab.getX() > oldX);
    }

    public static void main(String[] args) {
        testGasIncreasesSpeed();
        testBrakeDecreasesSpeed();
        testGasAcceptsBoundaryValues();
        testBrakeAcceptsBoundaryValues();
        testGasRejectsAmountAboveOne();
        testGasRejectsNegativeAmount();
        testBrakeRejectsAmountAboveOne();
        testBrakeRejectsNegativeAmount();
        testGasCannotDecreaseSpeed();
        testBrakeCannotIncreaseSpeed();
        testSpeedNeverExceedsEnginePower();
        testSpeedNeverBecomesNegative();
        testVolvoGasIncreasesSpeed();
        testTurbo();
        testForwardMoveIncreasesY();
        testRightMoveIncreasesX();
        testTurnLeftChangesDirection();
        testTurnRightChangesDirection();
    }
}