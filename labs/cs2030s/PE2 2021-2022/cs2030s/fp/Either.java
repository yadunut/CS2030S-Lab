package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

public abstract class Either<L, R> {
  public static <L, R> Either<L, R> left(L value) {
    return new Left<L, R>(value);
  }

  public static <L, R> Either<L, R> right(R value) {
    return new Right<L, R>(value);
  }

  public abstract boolean isLeft();

  public abstract boolean isRight();

  public abstract L getLeft();

  public abstract R getRight();

  public abstract <NL, NR> Either<NL, NR> map(
      Transformer<? super L, ? extends NL> leftMapper,
      Transformer<? super R, ? extends NR> rightMapper);

  public abstract <NL, NR> Either<NL, NR> flatMap(
      Transformer<? super L, Either<NL, NR>> leftMapper,
      Transformer<? super R, Either<NL, NR>> rightMapper);

  public abstract <U> U fold(
      Transformer<L, U> leftMapper,
      Transformer<R, U> rightMapper);

  public abstract Either<L, R> filterOrElse(
      BooleanCondition<R> predicate,
      Transformer<R, L> mapper);

  private static class Left<L, R> extends Either<L, R> {
    L value;

    private Left(L value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public boolean isRight() {
      return false;
    }

    @Override
    public L getLeft() {
      return this.value;
    }

    @Override
    public R getRight() {
      throw new NoSuchElementException();
    }

    @Override
    public <NL, NR> Either<NL, NR> map(
        Transformer<? super L, ? extends NL> leftMapper,
        Transformer<? super R, ? extends NR> rightMapper) {
      return Either.left(leftMapper.transform(this.value));
    }

    @Override
    public <NL, NR> Either<NL, NR> flatMap(
        Transformer<? super L,  Either< NL, NR>> leftMapper, 
        Transformer<? super R,  Either< NL, NR>> rightMapper) {
          Either< NL,  NR> transform = leftMapper.transform(this.value);
      return leftMapper.transform(this.value);
    }

    @Override
    public <U> U fold(Transformer<L, U> leftMapper, Transformer<R, U> rightMapper) {
      return leftMapper.transform(this.value);
    }

    @Override
    public Either<L, R> filterOrElse(BooleanCondition<R> predicate, Transformer<R, L> rightMapper) {
      return this;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o instanceof Left<?, ?>) {
        Left<?, ?> tmp = (Left<?, ?>) o;
        if (this.value == tmp.value) {
          return true;
        }
        if (this.value.equals(tmp.value)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String toString() {
      return "Left[" + this.value + "]";
    }
  }

  private static class Right<L, R> extends Either<L, R> {
    R value;

    private Right(R value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return false;
    }

    @Override
    public boolean isRight() {
      return true;
    }

    @Override
    public L getLeft() {
      throw new NoSuchElementException();
    }

    @Override
    public R getRight() {
      return this.value;
    }

    @Override
    public <NL, NR> Either<NL, NR> map(
        Transformer<? super L, ? extends NL> leftMapper,
        Transformer<? super R, ? extends NR> rightMapper) {
      return Either.right(rightMapper.transform(this.value));
    }

    @Override
    public <NL, NR> Either<NL, NR> flatMap(
        Transformer<? super L, Either<NL, NR>> leftMapper,
        Transformer<? super R, Either<NL, NR>> rightMapper) {
      return rightMapper.transform(this.value);
    }

    @Override
    public <U> U fold(Transformer<L, U> leftMapper, Transformer<R, U> rightMapper) {
      return rightMapper.transform(this.value);
    }

    @Override
    public Either<L, R> filterOrElse(BooleanCondition<R> predicate, Transformer<R, L> mapper) {
      if (predicate.test(this.value)) {
        return this;
      }
      return Either.left(mapper.transform(this.value));
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o instanceof Right<?, ?>) {
        Right<?, ?> tmp = (Right<?, ?>) o;
        if (this.value == tmp.value) {
          return true;
        }
        if (this.value.equals(tmp.value)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String toString() {
      return "Right[" + this.value + "]";
    }
  }

