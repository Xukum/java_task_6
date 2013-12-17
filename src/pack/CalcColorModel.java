package pack;

import secondName.CameraContainer;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/16/13
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalcColorModel {
    public int n = 3;
    public double k = 0.1;

    public Color calcColor(TriangleContainer triangleContainer, LightContainer lightContainer, CameraContainer cameraContainer) {
        double L[] = {lightContainer.lightPosition.x - triangleContainer.middleTrianglePoint.x, lightContainer.lightPosition.y - triangleContainer.middleTrianglePoint.y, lightContainer.lightPosition.z - triangleContainer.middleTrianglePoint.z};
        double LBack[] = {-L[0], -L[1], -L[2]};
        double LLenth = (L[0] * L[0] + L[1] * L[1] + L[2] * L[2]);

        double V[] = {cameraContainer.c.x - triangleContainer.middleTrianglePoint.x, cameraContainer.c.y - triangleContainer.middleTrianglePoint.y, cameraContainer.c.z - triangleContainer.middleTrianglePoint.z};
        double VLenth =  (V[0] * V[0] + V[1] * V[1] + V[2] * V[2]);

        double NLenth = (triangleContainer.normal[0] * triangleContainer.normal[0] + triangleContainer.normal[1] * triangleContainer.normal[1] + triangleContainer.normal[2] * triangleContainer.normal[2]);

        double RCoef = 2*(LBack[0]*triangleContainer.normal[0] + LBack[1]*triangleContainer.normal[1] + LBack[2]*triangleContainer.normal[2]) / NLenth;
        double R[]={LBack[0] - triangleContainer.normal[0]*RCoef, LBack[1] - triangleContainer.normal[1]*RCoef, LBack[2] - triangleContainer.normal[2]*RCoef};
        double RLenth =  (R[0] * R[0] + R[1] * R[1] + R[2] * R[2]);

        double cosTetta = (triangleContainer.normal[0] * L[0] + triangleContainer.normal[1] * L[1] + triangleContainer.normal[2] * L[2]) / (NLenth * LLenth);
        double cosAlfaN= Math.pow( (R[0] * L[0] + R[1] * L[1] + R[2] * L[2]) / (RLenth*VLenth ),n);

        double Id[] = {triangleContainer.Kd[0] * cosTetta, triangleContainer.Kd[1] * cosTetta, triangleContainer.Kd[2] * cosTetta};
        double If[] = {triangleContainer.Kf[0] * cosAlfaN ,triangleContainer.Kf[1] * cosAlfaN, triangleContainer.Kf[2] * cosAlfaN};

        return new Color((int)(Id[0] + If[0]),(int)(Id[1] + If[1]),(int)(Id[2] + If[2]));
    }
}
