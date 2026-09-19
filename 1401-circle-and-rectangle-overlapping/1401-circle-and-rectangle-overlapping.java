class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        // Find closest x-coordinate on rectangle
        int closestX = Math.max(x1, Math.min(xCenter, x2));

        // Find closest y-coordinate on rectangle
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Calculate squared distance
        int dx = closestX - xCenter;
        int dy = closestY - yCenter;

        int distanceSquared = dx * dx + dy * dy;

        // Circle overlaps/touches rectangle
        return distanceSquared <= radius * radius;
    }
}