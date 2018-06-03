package model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Board {

  public static final int ROWS = 6;
  public static final int COLUMNS = 7;

  private Chip[][] chips;

  /**
   * @return the chips
   */
  public Chip[][] getChips() {
    return chips;
  }

  /**
   * @param chips the chips to set
   */
  public void setChips(Chip[][] chips) {
    this.chips = chips;
  }

}
