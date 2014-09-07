package tk.bartbart333.citybuilder.math;

public class Vector3f {

    public float x;
    public float y;
    public float z;
    
    public Vector3f() {
    	this(0, 0, 0);
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getLength() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3f v) {
        return x * v.getX() + y * v.getY() + z * v.getZ();
    }

    public Vector3f cross(Vector3f v) {
        float x_ = y * v.getZ() - z * v.getY();
        float y_ = z * v.getX() - x * v.getZ();
        float z_ = x * v.getY() - y * v.getX();

        return new Vector3f(x_, y_, z_);
    }
    
    public Vector3f normalize() {
    	float length = getLength();
    	
    	x /= length;
    	y /= length;
    	z /= length;
    	
    	return new Vector3f(x, y, z);
    }

    public Vector3f normalized() {
        float length = getLength();

        float x_ = x / length;
        float y_ = y / length;
        float z_ = z / length;

        return new Vector3f(x_, y_, z_);
    }

    public Vector3f rotate(float angle, Vector3f axis) {
        float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

        float rx = axis.getX() * sinHalfAngle;
        float ry = axis.getY() * sinHalfAngle;
        float rz = axis.getZ() * sinHalfAngle;
        float rw = cosHalfAngle;

        Quaternion rotation = new Quaternion(rx, ry, rz, rw);
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.mul(this).mul(conjugate);

        x = w.getX();
        y = w.getY();
        z = w.getZ();

        return this;
    }

    public Vector3f add(Vector3f v) {
        return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    public Vector3f add(float f) {
        return new Vector3f(x + f, y + f, z + f);
    }

    public Vector3f sub(Vector3f v) {
        return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
    }

    public Vector3f sub(float f) {
        return new Vector3f(x - f, y - f, z - f);
    }

    public Vector3f mul(Vector3f v) {
        return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
    }

    public Vector3f mul(float f) {
        return new Vector3f(x * f, y * f, z * f);
    }

    public Vector3f div(Vector3f v) {
        return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
    }

    public Vector3f div(float f) {
        return new Vector3f(x / f, y / f, z / f);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
