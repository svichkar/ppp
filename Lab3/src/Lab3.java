public class Lab3 {

	public static void main(String[] args) {

		// 1
		String java = "Java";
		String forever = "forever";
		System.out.println("***First task***");
		System.out.println(concatByPlus(java, forever));
		System.out.println(concatByFormat(java, forever));
		System.out.println(concatByConcat(java, forever));
		System.out.println(concatByStringBuilder(java, forever));
		System.out.println(concatByStringBuffer(java, forever));

		// 2
		System.out.println("***Second task***");
		String fullName = "Мирон Богданович Маркевич";
		System.out.println("Initials: " + initials(fullName));

		// 3
		System.out.println("***Third task***");
		String firstPhrase = "THE MONA LISA";
		String secondPhrase = "Ah, not a smile?";
		anagram(firstPhrase, secondPhrase);
	}

	public static String concatByPlus(String firstWord, String secondWord) {
		return firstWord + " " + secondWord;
	}

	public static String concatByFormat(String firstWord, String secondWord) {
		return String.format("%s %s", firstWord, secondWord);
	}

	public static String concatByConcat(String firstWord, String secondWord) {
		return firstWord.concat(" ").concat(secondWord);
	}

	public static String concatByStringBuilder(String firstWord, String secondWord) {
		StringBuilder sb = new StringBuilder();
		return sb.append(firstWord).append(" ").append(secondWord).toString();
	}

	public static String concatByStringBuffer(String firstWord, String secondWord) {
		StringBuffer sbf = new StringBuffer();
		return sbf.append(firstWord).append(" ").append(secondWord).toString();
	}

	public static String initials(String fullName) {
		String[] parts = fullName.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String part : parts) {
			sb.append(part.charAt(0));
		}
		return sb.toString();
	}

	public static void anagram(String firstPhrase, String secondPhrase) {
		String charactersFromFirstPhrase = firstPhrase.replaceAll("\\W+", "");
		String charactersFromSecondPhrase = secondPhrase.replaceAll("\\W+", "");
		if (charactersFromFirstPhrase.length() != charactersFromSecondPhrase.length()) {
			System.out.println(String.format("Phrases '%s' and '%s' are NOT anagrams of each other", firstPhrase, secondPhrase));
		} else {
			String temp = charactersFromFirstPhrase;
			for (char letter : charactersFromSecondPhrase.toCharArray()) {
				if (!temp.toLowerCase().contains(String.valueOf(letter).toLowerCase())) {
					break;
				} else {
					temp = temp.toLowerCase().replaceFirst(String.valueOf(letter).toLowerCase(), "");
				}
			}
			if (temp.length() != 0) {
				System.out.println(String.format("Phrases '%s' and '%s' are NOT anagrams of each other", firstPhrase, secondPhrase));
			} else {
				System.out.println(String.format("Phrases '%s' and '%s' are anagrams of each other", firstPhrase, secondPhrase));
			}
		}
	}
}
