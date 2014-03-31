
package esendex.sdk.parser;

import com.thoughtworks.xstream.converters.extended.ISO8601DateConverter;

/**
 * Date converter that converts empty elements to null.
 * @author Mike Whittaker
 */
public class EmptyToNullDateConverter extends ISO8601DateConverter {
	
	/**
	 * Instantiates a new empty to null date converter.
	 */
	public EmptyToNullDateConverter() {
		super();
	}
	
	/**
	 * Parses xml date element as per DateConverter except that
	 * empty elements are converted to null.
	 * @param str The element data to convert
	 * @return an Object of type {@link esendex.sdk.util.Date}
	 */
	@Override
	public Object fromString(String str) {	
		if ("".equals(str)) return null;
		return super.fromString(str);
	}
	
}
