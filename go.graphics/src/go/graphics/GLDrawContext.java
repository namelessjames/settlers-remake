package go.graphics;

import go.graphics.text.EFontSize;
import go.graphics.text.TextDrawer;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/**
 * This is the main OpenGL context
 * 
 * @author michael
 */
public interface GLDrawContext {

	/**
	 * Fills a quad.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	void fillQuad(float x1, float y1, float x2, float y2);

	/**
	 * Draws a line between an array of points
	 * 
	 * @param points
	 *            The array of points. It's length has to be a multiple of 3.
	 * @param loop
	 *            If the line should be closed.
	 */
	void drawLine(float[] points, boolean loop);

	void glPushMatrix();

	void glTranslatef(float x, float y, float z);

	void glScalef(float x, float y, float z);

	void glPopMatrix();

	/**
	 * Set the color of the context.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void color(float red, float green, float blue, float alpha);

	/**
	 * Returns a texture id which is positive or 0. It returns a negative number on error.
	 * 
	 * @param width
	 * @param height
	 *            The height of the image.
	 * @param data
	 *            The data as array. It needs to have a length of width * height and each element is a color with: 5 bits red, 5 bits gree,n, 5 bits
	 *            blue and 1 bit alpha.
	 * @return The id of the generated texture.
	 */
	int generateTexture(int width, int height, ShortBuffer data);

	/**
	 * Deletes a texture.
	 * 
	 * @param textureid
	 */
	void deleteTexture(int textureid);

	/**
	 * Draws with a texture.
	 * 
	 * @param textureid
	 *            The id of the texture
	 * @param geometry
	 *            A float array of the form: x,y,z,u,v
	 */
	void drawQuadWithTexture(int textureid, float[] geometry);

	void drawQuadWithTexture(int textureid, int geometryindex);

	void drawTrianglesWithTexture(int textureid, float[] geometry);

	void drawTrianglesWithTexture(int textureid, int geometryindex, int triangleCount);

	void drawTrianglesWithTextureColored(int textureid, float[] geometry);

	void drawTrianglesWithTextureColored(int textureid, int geometryindex, int triangleCount);

	int makeWidthValid(int width);

	int makeHeightValid(int height);

	void glMultMatrixf(float[] matrix, int offset);

	/**
	 * Updates a part of a texture image.
	 * 
	 * @param textureIndex
	 *            The texture to use.
	 * @param left
	 * @param bottom
	 * @param width
	 * @param height
	 * @param data
	 */
	void updateTexture(int textureIndex, int left, int bottom, int width, int height, ShortBuffer data);

	TextDrawer getTextDrawer(EFontSize size);

	int storeGeometry(float[] geometry);

	boolean isGeometryValid(int geometryindex);

	void removeGeometry(int geometryindex);

	GLBuffer startWriteGeometry(int geometryindex);

	void endWriteGeometry(int geometryindex);

	int generateGeometry(int bytes);

	interface GLBuffer {
		void putFloat(float f);

		void putByte(byte b);

		void position(int position);
	}

	void drawTrianglesWithTextureColored(int currentTexture,
			ByteBuffer byteBuffer, int currentTriangles);
}
