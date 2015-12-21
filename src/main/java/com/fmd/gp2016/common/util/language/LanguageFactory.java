/**
 * @author mohamed265
 * Created On : Dec 11, 2015 2:50:18 PM
 */
package com.fmd.gp2016.common.util.language;

import com.fmd.gp2016.common.util.jsf.annotation.SpringApplicationScoped;

/**
 * @author mohamed265
 */
@SpringApplicationScoped
public class LanguageFactory {

	private static ArabicLanguage arabicLanguage;

	private static EnglishLanguage englishLanguage;

	public LanguageFactory() {
		System.out.println("djdjdk");
	}

	public static ArabicLanguage getArabicLanguage() {
		if (arabicLanguage == null)
			arabicLanguage = new ArabicLanguage();
		return arabicLanguage;
	}

	public static EnglishLanguage getEnglishLanguage() {
		if (englishLanguage == null)
			englishLanguage = new EnglishLanguage();
		return englishLanguage;
	}

}
