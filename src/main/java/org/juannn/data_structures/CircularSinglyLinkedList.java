package org.juannn.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularSinglyLinkedList<T> implements Iterable<T> {

    private Node <T> head;
    private Node<T> tail;
    private int size;

    public CircularSinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    // Add a new element to the end of the list
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.setNextNode(head);
        } else {
            tail.setNextNode(newNode); // Add the new element after the current tail
            tail = newNode; // Update the tail to point to the new node
            tail.setNextNode(head);
        }
        size++;
    }

    // Add a new element to the beginning of the list
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.setNextNode(head);
        } else {
            newNode.setNextNode(head); // Set the new node's next to the current head
            head = newNode; // Update the head to the new node
            tail.setNextNode(head);
        }
        size++;
    }

    public T remove(T element){

        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        if (element.equals(head.getElement())){
            return removeFirst();
        }

        Node<T> current = head;
        Node<T> previus = tail;

        for (int i = 0; i < size; i++) {
            if(element.equals(current.getElement())){
                if(current == tail){
                    return removeLast();
                } else {
                    previus.setNextNode(current.getNextNode());
                    size--;
                    return element;
                }
            }
            previus = current;
            current = current.getNextNode();
        }
        throw new NoSuchElementException("Element not found");
    }

    // Remove the first element from the list
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        T element = head.getElement();

        //Check if the list is now empty:
        if (head == tail) {
            head = null;
            tail = null;
        }else{
            head = head.getNextNode();
            tail.setNextNode(head);
        }
        size--;
        return element;
    }
    // Remove the last element from the list

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        if (head == tail) { // Only one element
            return removeFirst();
        }

        Node<T> current = head;
        while (current.getNextNode() != tail) {
            current = current.getNextNode(); // Traverse to the second-to-last node
        }
        T element = tail.getElement();
        tail = current; // Update the tail to the new last node
        tail.setNextNode(head); // Fix circular reference
        size--;
        return element;
    }
    // Get the first node in the list
    public T getFirst() {
        return head.getElement();
    }

    // Get the last node in the list
    public T getLast() {
        return tail.getElement();
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Print all elements in the list from head to tail
    public void printForward() {

        if(isEmpty()){
            System.out.println("List is empty");
        }

        Node<T> current = head;
        do{
            System.out.print(current.getElement());
            current = current.getNextNode();
        }
        while (current != head);
    }

    // Iterator implementation for the list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private boolean firstIteration = true;

            @Override
            public boolean hasNext() {
                return current != null && (firstIteration || current != head);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = current.getElement();
                current = current.getNextNode();
                firstIteration = false;
                return element;
            }
        };
    }
}
