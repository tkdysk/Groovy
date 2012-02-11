package sample.spock;

import artistlist.GestArtistRank;
import spock.lang.Specification;

class EmptyStack extends Specification {
  def stack = new Stack()

  def "size"() {
    expect: stack.size() == 0
  }

  def "pop"() {
    when: stack.pop()
    then: thrown(EmptyStackException)
  }

  def "peek"() {
    when: stack.peek()
    then: thrown(EmptyStackException)
  }

  def "push"() {
    when:
    stack.push("elem")

    then:
    stack.size() == 1
    stack.peek() == "elem"
  }
}