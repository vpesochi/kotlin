package kotlin.util

import java.util.*

/** Returns true if any elements in the collection match the given predicate */
inline fun <T> java.lang.Iterable<T>.any(predicate: (T)-> Boolean) : Boolean {
  for (elem in this) {
    if (predicate(elem)) {
      return true
    }
  }
  return false
}

/** Returns true if all elements in the collection match the given predicate */
inline fun <T> java.lang.Iterable<T>.all(predicate: (T)-> Boolean) : Boolean {
  for (elem in this) {
    if (!predicate(elem)) {
      return false
    }
  }
  return true
}

/** Returns the number of items which match the given predicate  */
inline fun <T> java.lang.Iterable<T>.count(predicate: (T)-> Boolean) : Int {
  var answer = 0
  for (elem in this) {
    if (predicate(elem))
      answer += 1
  }
  return answer
}

/** Returns the first item in the collection which matches the given predicate or null if none matched */
inline fun <T> java.lang.Iterable<T>.find(predicate: (T)-> Boolean) : T? {
  for (elem in this) {
    if (predicate(elem))
      return elem
  }
  return null
}

/** Returns a new List containing all elements in this collection which match the given predicate */
inline fun <T> java.lang.Iterable<T>.filter(predicate: (T)-> Boolean) : Collection<T> = filterTo(java.util.ArrayList<T>(), predicate)

/** Filters all elements in this collection which match the given predicate into the given result collection */
inline fun <T, C: Collection<in T>> java.lang.Iterable<T>.filterTo(result: C, predicate: (T)-> Boolean) : C {
  for (elem in this) {
    if (predicate(elem))
      result.add(elem)
  }
  return result
}

/** Returns a List containing all the non null elements in this collection */
inline fun <T> java.lang.Iterable<T?>?.filterNulls() : Collection<T> = filterNullsTo(java.util.ArrayList<T>())

/** Filters all the null elements in this collection winto the given result collection */
inline fun <T, C: Collection<in T>> java.lang.Iterable<T?>?.filterNullsTo(result: C) : C {
    if (this != null) {
        for (elem in this) {
            if (elem != null)
                result.add(elem)
        }
    }
    return result
}

/** Returns a new collection containing all elements in this collection which do not match the given predicate */
inline fun <T> java.lang.Iterable<T>.filterNot(predicate: (T)-> Boolean) : Collection<T> =  filterNotTo(ArrayList<T>(), predicate)

/** Returns a new collection containing all elements in this collection which do not match the given predicate */
inline fun <T, C: Collection<in T>> java.lang.Iterable<T>.filterNotTo(result: C, predicate: (T)-> Boolean) : C {
  for (elem in this) {
    if (!predicate(elem))
      result.add(elem)
  }
  return result
}

/**
  * Returns the result of transforming each item in the collection to a one or more values which
  * are concatenated together into a single collection
  */
inline fun <T, R> java.lang.Iterable<T>.flatMap(transform: (T)-> Collection<R>) : Collection<R> {
    return flatMapTo(ArrayList<R>(), transform)
}

/**
  * Returns the result of transforming each item in the collection to a one or more values which
  * are concatenated together into a single collection
  */
inline fun <T, R> java.lang.Iterable<T>.flatMapTo(result: Collection<R>, transform: (T)-> Collection<R>) : Collection<R> {
  for (elem in this) {
    val coll = transform(elem)
    if (coll != null) {
      for (r in coll) {
        result.add(r)
      }
    }
  }
  return result
}

/** Performs the given operation on each element inside the collection */
inline fun <T> java.lang.Iterable<T>.foreach(operation: (element: T) -> Unit) {
  for (elem in this)
    operation(elem)
}

/**
 * Folds all the values from from left to right with the initial value to perform the operation on sequential pairs of values
 *
 * For example to sum together all numeric values in a collection of numbers it would be
 * {code}val total = numbers.fold(0){(a, b) -> a + b}{code}
 */
inline fun <T> java.lang.Iterable<T>.fold(initial: T, operation: (it: T, it2: T) -> T): T {
  var answer = initial
  for (elem in this) {
    answer = operation(answer, elem)
  }
  return answer
}

/**
 * Folds all the values from right to left with the initial value to perform the operation on sequential pairs of values
 */
inline fun <T> java.lang.Iterable<T>.foldRight(initial: T, operation: (it: T, it2: T) -> T): T {
  val reversed = this.reverse()
  return reversed.fold(initial, operation)
}

/**
 * Iterates through the collection performing the transformation on each element and using the result
 * as the key in a map to group elements by the result
 */
inline fun <T,K> java.lang.Iterable<T>.groupBy(result: Map<K,List<T>> = HashMap<K,List<T>>(), toKey: (T)-> K) : Map<K,List<T>> {
  for (elem in this) {
    val key = toKey(elem)
    val list = result.getOrPut(key){ ArrayList<T>() }
    list.add(elem)
  }
  return result
}


/** Creates a String from all the elements in the collection, using the seperator between them and using the given prefix and postfix if supplied */
inline fun <T> java.lang.Iterable<T>.join(separator: String, prefix: String = "", postfix: String = "") : String {
  val buffer = StringBuilder(prefix)
  var first = true
  for (elem in this) {
    if (first)
      first = false
    else
      buffer.append(separator)
    buffer.append(elem)
  }
  buffer.append(postfix)
  return buffer.toString().sure()
}

/** Returns a reversed List of this collection */
inline fun <T> java.lang.Iterable<T>.reverse() : List<T> {
  val answer = LinkedList<T>()
  for (elem in this) {
    answer.addFirst(elem)
  }
  return answer
}

/** Copies the collection into the given collection */
inline fun <T, C: Collection<T>> java.lang.Iterable<T>.to(result: C) : C {
  for (elem in this)
    result.add(elem)
  return result
}

/** Converts the collection into a LinkedList */
inline fun <T> java.lang.Iterable<T>.toLinkedList() : LinkedList<T> = this.to(LinkedList<T>())

/**  Converts the collection into a List */
inline fun <T> java.lang.Iterable<T>.toList() : List<T> = this.to(ArrayList<T>())

/** Converts the collection into a Set */
inline fun <T> java.lang.Iterable<T>.toSet() : Set<T> = this.to(HashSet<T>())

/** Converts the collection into a SortedSet */
inline fun <T> java.lang.Iterable<T>.toSortedSet() : SortedSet<T> = this.to(TreeSet<T>())
/**
  TODO figure out necessary variance/generics ninja stuff... :)
inline fun <in T> java.lang.Iterable<T>.toSortedList(transform: fun(T) : java.lang.Comparable<*>) : List<T> {
  val answer = this.toList()
  answer.sort(transform)
  return answer
}
*/
