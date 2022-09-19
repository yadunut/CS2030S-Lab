/**
 * A non-generic Action to print the String
 * representation of the object.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Yadunand Prem (10B)
 */

class Print implements Action<Object> {

  @Override
  public void call(Object item) {
    System.out.println(item);
  }

}
