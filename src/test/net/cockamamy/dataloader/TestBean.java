package net.cockamamy.dataloader;

import static java.lang.String.*;

public final class TestBean {

	public static final String FIRST_NAME_PROP_NAME = "firstName";
	public static final String LAST_NAME_PROP_NAME = "lastName";
	public static final String FAVORITE_COLOR = "favoriteColor";

	private final String myFirstName;
	private final String myLastName;
	private final String myFavoriteColor;

	public TestBean(String aFirstName, String aLastName, String aFavoriteColor) {

		super();

		this.myFirstName = aFirstName;
		this.myLastName = aLastName;
		this.myFavoriteColor = aFavoriteColor;

	}

	// BEGIN: Object implementation
	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode += (17 * aHashCode) + this.myFirstName.hashCode();
		aHashCode += (17 * aHashCode) + this.myLastName.hashCode();
		aHashCode += (17 * aHashCode) + this.myFavoriteColor.hashCode();

		return aHashCode;

	}

	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null && this.getClass().equals(thatObject.getClass())) {

			TestBean thatBean = (TestBean) thatObject;

			if (this.myFirstName.equals(thatBean.getFirstName())
					&& this.myLastName.equals(thatBean.getLastName())
					&& this.myFavoriteColor.equals(thatBean.getFavoriteColor())) {

				return true;

			}

		}

		return false;

	}

	@Override
	public String toString() {

		return format(
				"TestBean (firstName: %1$s, lastName: %2$s, favoriteColor: %3$s)",
				this.myFirstName, this.myLastName, this.myFavoriteColor);

	}

	// END: Object implementation

	public final String getFirstName() {

		return this.myFirstName;

	}

	public final String getLastName() {

		return this.myLastName;

	}

	public final String getFavoriteColor() {

		return this.myFavoriteColor;

	}

}
