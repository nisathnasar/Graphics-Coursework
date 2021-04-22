package exam;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import GraphicsLab.GraphicsLab;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;


public class exampart2 extends GraphicsLab {

	public static void main(String args[]) {
		new exampart2().run(WINDOWED, "exampart2", 0.01f);

	}

	public void drawCube(Colour col) {
		Vertex v1 = new Vertex(-0.5f, -0.5f, 0.5f);// bottom left
		Vertex v2 = new Vertex(-0.5f, 0.5f, 0.5f);// top left
		Vertex v3 = new Vertex(0.5f, 0.5f, 0.5f);// top right
		Vertex v4 = new Vertex(0.5f, -0.5f, 0.5f);// bottom right
		Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);// bottom left
		Vertex v6 = new Vertex(-0.5f, 0.5f, -0.5f);// top left
		Vertex v7 = new Vertex(0.5f, 0.5f, -0.5f);// top right
		Vertex v8 = new Vertex(0.5f, -0.5f, -0.5f);// bottom right

		col.submit();

		// draw the far face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v7.toVector(), v8.toVector(), v5.toVector()).submit();
			v6.submit();
			v7.submit();
			v8.submit();
			v5.submit();
		}
		GL11.glEnd();

		// draw the right face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v3.toVector(), v4.toVector(), v8.toVector()).submit();
			v7.submit();
			v3.submit();
			v4.submit();
			v8.submit();
		}
		GL11.glEnd();

		// draws left face;
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v6.toVector(), v5.toVector(), v1.toVector()).submit();
			v2.submit();
			v6.submit();
			v5.submit();
			v1.submit();
		}
		GL11.glEnd();

		// draw the near face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v2.toVector(), v1.toVector(), v4.toVector()).submit();
			GL11.glTexCoord2f(1.0f, 1.0f);
			v3.submit();
			GL11.glTexCoord2f(0.0f, 1.0f);
			v2.submit();
			GL11.glTexCoord2f(0.0f, 0.0f);
			v1.submit();
			GL11.glTexCoord2f(1.0f, 0.0f);
			v4.submit();
		}
		GL11.glEnd();

		// draw the top face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v6.toVector(), v2.toVector(), v3.toVector()).submit();
			v7.submit();
			v6.submit();
			v2.submit();
			v3.submit();
		}
		GL11.glEnd();

		// draw the bottom face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v1.toVector(), v5.toVector(), v8.toVector()).submit();
			v4.submit();
			v1.submit();
			v5.submit();
			v8.submit();
		}
		GL11.glEnd();

	}

	@Override
	protected void initScene() throws Exception {
		float globalAmbient[] = { 0.58f, 0.58f, 0.58f, 1.0f };
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
		float specular0[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		float diffuse0[] = { 0.85f, 0.85f, 0.85f, 1.0f };

		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		float position0[] = { 0.0f, 10.0f, -5.0f, 1.0f };
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(specular0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

		GL11.glEnable(GL11.GL_LIGHT0);
		
		float position1[] = { -5.0f, 20.0f, -15.0f, 1.0f };
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, FloatBuffer.wrap(specular0));
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, FloatBuffer.wrap(position1));

		GL11.glEnable(GL11.GL_LIGHT1);
		

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_NORMALIZE);

	}

	@Override
	protected void checkSceneInput() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateScene() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void renderScene() {
		GL11.glRotatef(10, 0, 1, 0);
		// head
		GL11.glPushMatrix();
		{
			float specular[] = { 0.58f, 0.58f, 0.58f, 1.0f };
			float diffuse[] = { 0.5f, 0.11f, 0.59f, 1.0f };
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 1);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));
			GL11.glTranslatef(0.0f, 1.25f, -8.0f);
			GL11.glScalef(0.7f, 0.7f, 1.0f);
			drawCube(Colour.WHITE);
		}
		GL11.glPopMatrix();

		// body
		GL11.glPushMatrix();
		{
			float specular[] = { 0.58f, 0.58f, 0.58f, 1.0f };
			float diffuse[] = { 0.5f, 0.5f, 0.59f, 1.0f };
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 1);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));
			GL11.glTranslatef(0.0f, 0.0f, -8.0f);
			GL11.glScalef(1.4f, 1.8f, 1.0f);
			drawCube(Colour.BLUE);
		}
		GL11.glPopMatrix();

		// right arm
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-0.9f, 0.15f, -8.0f);
			GL11.glScalef(0.3f, 1.5f, 1.0f);
			drawCube(Colour.RED);
		}
		GL11.glPopMatrix();

		// left arm
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0.9f, 0.15f, -8.0f);
			GL11.glScalef(0.3f, 1.5f, 1.0f);
			drawCube(Colour.RED);
		}
		GL11.glPopMatrix();

		// right leg
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-0.45f, -1.72f, -8.0f);
			GL11.glScalef(0.5f, 1.6f, 1.0f);
			drawCube(Colour.CYAN);
		}
		GL11.glPopMatrix();

		// left leg
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0.45f, -1.72f, -8.0f);
			GL11.glScalef(0.5f, 1.6f, 1.0f);
			drawCube(Colour.CYAN);
		}
		GL11.glPopMatrix();

	}

}