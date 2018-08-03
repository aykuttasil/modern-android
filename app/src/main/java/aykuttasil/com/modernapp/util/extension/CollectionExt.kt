package aykuttasil.com.modernapp.util.extension

fun <T> ArrayList<T>.addIfAbsent(data: T) {
    if (!this.contains(data)) {
        this.add(data)
    }
}

fun <T> ArrayList<T>.removeIfExist(data: T) {
    if (this.contains(data)) {
        this.remove(data)
    }
}