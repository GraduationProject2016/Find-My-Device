
package com.fmd.gp2016.common.util.language;

import com.fmd.gp2016.common.util.jsf.annotation.SpringApplicationScoped;

@SpringApplicationScoped
public class LanguageFactory {

	private static ArabicLanguage arabicLanguage;

	private static EnglishLanguage englishLanguage;

	public LanguageFactory() {
		System.out.println("application scope");
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
