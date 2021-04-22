/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *
 *  TODO: Provide a scene graph for your submission
 */
package coursework.nisath;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.*;

/**
 * TODO: Briefly describe your submission here
 *
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 *
 */
public class temp extends GraphicsLab
{
	private final int backWall = 1;
	private final int groundList = 2;
	private final int sideWall = 3;
	private final int pinList = 4;
	private final int retrieverList = 5;
	private final int railingList = 6;
	private final int teleList = 7;
	private int count = 0;

	private float sceneRotateSpeed = 1000f;
	private float ballDiameter = 0.75f;
	private float ballX = 0f;
	private final float defaultBowlSpeed = 400f;
	private float bowlSpeed = defaultBowlSpeed;
	private float zGap = -2.5f;
	private float xGap = 2.9f;
	private float rowPosition1 = -35f;
	private float rowPosition2 = rowPosition1 + zGap;
	private float rowPosition3 = rowPosition2 + zGap;
	private float rowPosition4 = rowPosition3 + zGap;
	private float ballPosition = 0f;
	private float sceneRotationAngle = 0f;
	private float pinRaiseValue1 = 0f;
	private float pinRaiseValue2 = 0f;
	private float pinRaiseValue3 = 0f;
	private float pinRaiseValue4 = 0f;
	private float pinSideMoveValue = 0f;
	private float pinSideMoveValue1 = 0f;
	private float pinSideMoveValue2 = 0f;
	private float pinSideMoveValue3 = 0f;
	private float pinSideMoveValue4 = 0f;
	private float ballRaiseValue = ballDiameter;
	private float rotationOfRow1 = 0f;
	private float rotationOfRow2 = 0f;
	private float rotationOfRow3 = 0f;
	private float rotationOfRow4 = 0f;
	private float cameraViewPointX = 7f;
	private float cameraViewPointY = 0f;
	private float cameraViewPointZ = -15f;
	private float viewerLocX = -11f;
	private float viewerLocY = 6f;
	private float viewerLocZ = 10f;
	private float viewUpVectorX = 0f;
	private float viewUpVectorY = 1f;
	private float viewUpVectorZ = 0f;

	private boolean bowlKeyRelease = false;
	private boolean passedRow1 = false;
	private boolean passedRow2 = false;
	private boolean passedRow3 = false;
	private boolean passedRow4 = false;
	private boolean isRowFall1 = false;
	private boolean isRowFall2 = false;
	private boolean isRowFall3 = false;
	private boolean isRowFall4 = false;
	private boolean reset = false;

	private Texture groundTextures, sideWallTexture, tvTexture, tvStrikeTexture;
	private Colour ballColour, wallColour;
	private Meshes meshes;
    //TODO: Feel free to change the window title and default animation scale here
    public static void main(String args[])
    {   new temp().run(WINDOWED,"CS2150 Coursework Submission",0.01f);
    }

    protected void initScene() throws Exception
    {//TODO: Initialise your resources here - might well call other methods you write.
    }
    protected void checkSceneInput()
    {//TODO: Check for keyboard and mouse input here
    }
    protected void updateScene()
    {
        //TODO: Update your scene variables here - remember to use the current animation scale value
        //        (obtained via a call to getAnimationScale()) in your modifications so that your animations
        //        can be made faster or slower depending on the machine you are working on
    }
    protected void renderScene()
    {//TODO: Render your scene here - remember that a scene graph will help you write this method! 
     //      It will probably call a number of other methods you will write.
    }
    protected void setSceneCamera()
    {
        // call the default behaviour defined in GraphicsLab. This will set a default perspective projection
        // and default camera settings ready for some custom camera positioning below...  
        super.setSceneCamera();

        //TODO: If it is appropriate for your scene, modify the camera's position and orientation here
        //        using a call to GL11.gluLookAt(...)
   }

    protected void cleanupScene()
    {//TODO: Clean up your resources here
    }

}