package supremo;
import robocode.*;
import java.awt.Color;

public class RoboTapajos extends AdvancedRobot {

    private double angulo = 0;
    private boolean direcao = true;
    private boolean mudarRota = false;
    private Enemy maisProximo = null;

    public void run() {
        setBodyColor(new Color(0, 0, 255));
        setGunColor(new Color(255, 255, 255));
        setRadarColor(new Color(255, 255, 255));

        while (true) {
            setTurnRadarRight(360);

            if (getRadarTurnRemaining() == 0) {
                if (mudarRota) {
                    mudarRota = false;
                    angulo = 0;
                    direcao = !direcao;
                }

                maisProximo = null;
                double distanciaMinima = Double.MAX_VALUE;

                ScannedRobotEvent e = getScannedRobotEvent();
                while (e != null) {
                    double distancia = e.getDistance();
                    if (distancia > 150 && distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        maisProximo = new Enemy(e.getBearing(), e.getHeading(), e.getVelocity());
                    }
                    e = getScannedRobotEvent();
                }

                if (maisProximo != null) {
                    fireBullet(2.5, calcularAnguloMira(maisProximo));
                }

                angulo += velocidadeAngular * getTime();

                if (angulo >= 90 && direcao) {
                    direcao = false;
                    setAhead(velocidadeLinear);
                } else if (angulo < 0 && !direcao) {
                    direcao = true;
                    setAhead(velocidadeLinear);
                }

                ajustarVelocidade();
            }

            execute();
        }
    }

    public void onHitByBullet(BulletHitEvent e) {
        mudarRota = true;
    }

    public void onHitWall(HitWallEvent e) {
        mudarRota = true;
    }

    private double calcularAnguloMira(Enemy inimigo) {
        double anguloRelativo = inimigo.bearing - getHeading();
        double anguloMira = getHeading() + anguloRelativo + (inimigo.heading - anguloRelativo) * getTime() / inimigo.distancia;
        return anguloMira;
    }

    private void ajustarVelocidade() {
        if (angulo > 0 && angulo < 45) {
            setTurnRight(velocidadeAngular * 2);
        } else if (angulo > 45 && angulo < 90) {
            setTurnRight(velocidadeAngular);
        } else if (angulo > 270 && angulo < 315) {
            setTurnLeft(velocidadeAngular * 2);
        } else if (angulo > 315 && angulo < 360) {
            setTurnLeft(velocidadeAngular);
        } else {
            setTurnRight(0);
        }
    }

    private class Enemy {
        double bearing;
        double heading;
        double velocity;
        double distancia;

        public Enemy(double bearing, double heading, double velocity) {
            this.bearing = bearing;
            this.heading = heading;
            this.velocity = velocity;
            this.distancia = getScannedRobotEvent().getDistance();
        }
    }
}
