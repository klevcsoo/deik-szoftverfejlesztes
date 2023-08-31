package util;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The BoardPosition class represents a position on a board with column and row coordinates.
 * Numbering begins with 0 at the leftmost column and the uppermost row.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPosition {

    private int col = 0, row = 0;

    /**
     * Copies the column and row data from another object.
     * <p>
     * <code>
     * actualPosition.copyFrom(desiredPosition);
     * </code>
     *
     * @param other The other {@link BoardPosition} to copy data from.
     */
    public void copyFrom(BoardPosition other) {
        col = other.getCol();
        row = other.getRow();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BoardPosition that = (BoardPosition) o;
        return col == that.col && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }
}
