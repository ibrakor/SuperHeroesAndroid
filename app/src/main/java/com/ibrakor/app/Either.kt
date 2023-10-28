package com.ibrakor.ejercicioformulario02.app
sealed class Either<out L, out R> {

    data class Left<out T>(val value: T) : Either<T, Nothing>() {
        override fun isRight(): Boolean = false
        override fun isLeft(): Boolean = true
        override fun get(): Nothing = throw NoSuchElementException("com.example.ejercicioformulario02.app.Either.Right.value on Left")
    }

    data class Right<out T>(val value: T) : Either<Nothing, T>() {
        override fun isRight(): Boolean = true
        override fun isLeft(): Boolean = false
        override fun get(): T = value
    }

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean
    abstract fun get(): R

    inline fun <C> fold(left: (L) -> C, right: (R) -> C): C = when (this) {
        is Left -> left(value)
        is Right -> right(value)
    }

    inline fun <C> map(f: (R) -> C): Either<L, C> = fold(
        { Left(it) },
        { Right(f(it)) }
    )

    inline fun <C> mapLeft(f: (L) -> C): Either<C, R> = fold(
        { Left(f(it)) },
        { Right(it) }
    )

    inline fun <C, P> bimap(left: (L) -> C, right: (R) -> P): Either<C, P> =
        fold(
            { Left(left(it)) },
            { Right(right(it)) }
        )

    fun getOrNull(): R? = fold(
        { null },
        { it }
    )

    inline fun run(actionLeft: (L) -> Unit = {}, actionRight: (R) -> Unit = {}): Either<L, R> =
        also { either ->
            either.mapLeft { actionLeft(it) }
            either.map { actionRight(it) }
        }

    fun swap(): Either<R, L> = fold({ Right(it) }, { Left(it) })
}

fun <B> Either<*, B>.getOrElse(default: () -> B): B = fold(
    { default() },
    { it }
)

inline fun <L, R, S> Either<L, R>.flatMap(f: (R) -> Either<L, S>): Either<L, S> = fold(
    { Either.Left(it) },
    { f(it) }
)

fun <L, R> L.left(): Either<L, R> = Either.Left(this)
fun <L, R> R.right(): Either<L, R> = Either.Right(this)

fun <L, R, C> Either<L, R>.apply(resultAB: Either<L, (R) -> C>): Either<L, C> =
    flatMap { a -> resultAB.map { it(a) } }