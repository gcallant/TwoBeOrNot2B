package Item;

public interface Storable
{
	void use();

	int getPower();

	void store();

	boolean isStored();

	String getParentType();

	String getChildType();
}