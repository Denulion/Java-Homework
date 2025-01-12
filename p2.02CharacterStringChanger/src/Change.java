public class Change {
    private char fromCharacter;
    private char toCharacter;

    public Change(char fromCharacter, char toCharacter) {
        this.fromCharacter = fromCharacter;
        this.toCharacter = toCharacter;
    }
    public String change(String characterString) {
        String changedString = "";

        for (int i = 0; i < characterString.length(); i++) {
            char currentChar = characterString.charAt(i);
            if (currentChar == this.fromCharacter) {
                changedString += this.toCharacter;
            } else {
                changedString += currentChar;
            }
        }
        return changedString;
    }
}