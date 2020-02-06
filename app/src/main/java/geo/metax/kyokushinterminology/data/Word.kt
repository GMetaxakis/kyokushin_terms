package geo.metax.kyokushinterminology.data

data class Word(val wordType: WordType, val japanese: String, val english: String) {
    fun contains(filterPattern: String): Boolean =
        japanese.toLowerCase().contains(filterPattern) || english.toLowerCase().contains(filterPattern)
}