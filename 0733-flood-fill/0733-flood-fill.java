class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int originalColor = image[sr][sc];

        // If color is already same, no need to process
        if (originalColor == color) {
            return image;
        }

        dfs(image, sr, sc, originalColor, color);

        return image;
    }

    private void dfs(int[][] image,
                     int row,
                     int col,
                     int originalColor,
                     int newColor) {

        int rows = image.length;
        int cols = image[0].length;

        // Boundary check
        if (row < 0 || col < 0 ||
            row >= rows || col >= cols ||
            image[row][col] != originalColor) {

            return;
        }

        // Change color
        image[row][col] = newColor;

        // Visit all 4 directions
        dfs(image, row - 1, col, originalColor, newColor); // up
        dfs(image, row + 1, col, originalColor, newColor); // down
        dfs(image, row, col - 1, originalColor, newColor); // left
        dfs(image, row, col + 1, originalColor, newColor); // right
    }
}