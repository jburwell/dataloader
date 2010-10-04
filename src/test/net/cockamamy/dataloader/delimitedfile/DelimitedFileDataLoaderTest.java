package net.cockamamy.dataloader.delimitedfile;

import static java.io.File.*;
import static java.lang.String.*;

import static org.testng.Assert.*;

import static net.cockamamy.dataloader.TestBean.*;
import static net.cockamamy.dataloader.delimitedfile.DelimitedFileDataLoaderBuilder.*;

import java.io.*;
import java.util.*;

import net.cockamamy.dataloader.*;
import net.cockamamy.dataloader.util.converter.*;

import org.testng.annotations.*;

import com.google.common.collect.*;

@Test
public final class DelimitedFileDataLoaderTest {

	private Set<TestBean> myExpectedBeans;

	private DataLoader myDataLoader;

	@BeforeTest
	public void setup() {

		// Build the set of reference beans ...
		this.myExpectedBeans = this.buildBeans();

		// Create a delimited file data loader ...
		DelimitedFileDataLoaderBuilder aBuilder = new DelimitedFileDataLoaderBuilder();

		aBuilder.setInputFile(this.createDelimitedFile(this.myExpectedBeans));

		aBuilder.addColumnDefinition(FIRST_NAME_PROP_NAME,
				new StringPropertyConverter());
		aBuilder.addColumnDefinition(LAST_NAME_PROP_NAME,
				new StringPropertyConverter());
		aBuilder.addColumnDefinition(FAVORITE_COLOR,
				new StringPropertyConverter());

		this.myDataLoader = aBuilder.buildLoader();

	}

	private Set<TestBean> buildBeans() {

		ImmutableSet.Builder<TestBean> aBuilder = ImmutableSet.builder();

		aBuilder.add(new TestBean("John", "Doe", "blue"), new TestBean("Sally",
				"Smith", "red"), new TestBean("Abe", "Lincoln", "black"),
				new TestBean("Albus", "Dumbledore", "purple"), new TestBean(
						"Herminoe", "Grainger", "pink"));

		return aBuilder.build();

	}

	private File createDelimitedFile(Set<TestBean> theBeans) {

		File aDelimitedFile = null;

		try {

			// Create a temporary file ...
			aDelimitedFile = createTempFile(this.getClass().getName(), ".csv");
			aDelimitedFile.deleteOnExit();

			// Write the set of beans out to the file ...
			BufferedWriter aWriter = new BufferedWriter(new FileWriter(
					aDelimitedFile));
			for (TestBean aBean : theBeans) {

				StringBuilder aStringBuilder = new StringBuilder();

				aStringBuilder.append(aBean.getFirstName());
				aStringBuilder.append(DEFAULT_DELIMITER);
				aStringBuilder.append(aBean.getLastName());
				aStringBuilder.append(DEFAULT_DELIMITER);
				aStringBuilder.append(aBean.getFavoriteColor());

				aWriter.append(aStringBuilder);
				aWriter.newLine();

			}

			// File all buffers and close up shop ...
			aWriter.flush();
			aWriter.close();

		} catch (IOException e) {

			fail("Failed to create test delimited file.", e);

		}

		return aDelimitedFile;

	}

	@Test
	public void testBeanHashCode() {

		TestBean aBean = new TestBean("John", "Doe", "blue");
		TestBean thatBean = new TestBean("John", "Doe", "blue");

		assertEquals(thatBean, aBean);
		assertEquals(thatBean.hashCode(), aBean.hashCode());

	}

	@Test
	public void testLoadData() {

		final Set<TestBean> theLoadedBeans = new HashSet<TestBean>();

		this.myDataLoader.loadData(new DataConsumer() {

			public void consume(Map<String, Object> aRecord) {

				theLoadedBeans.add(new TestBean((String) aRecord
						.get(FIRST_NAME_PROP_NAME), (String) aRecord
						.get(LAST_NAME_PROP_NAME), (String) aRecord
						.get(FAVORITE_COLOR)));

			}

		});

		assertEquals(theLoadedBeans.size(), this.myExpectedBeans.size());
		assertTrue(
				this.myExpectedBeans.containsAll(theLoadedBeans),
				format("%1$s does not match %2$s", theLoadedBeans,
						this.myExpectedBeans));

	}

}
