/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.List;

/**
 *
 * @author Alex Ras
 */
public class Node<E>{

	private E value;
	
	private Node leftChild, rightChild;
   
	public Node(E value) {
   	this.value = value;
	}
   public Node(E value, Node<E> leftChild, Node<E> rightChild){
   	this.value = value;
       	this.leftChild = leftChild;
       	this.rightChild = rightChild;
   }
   
   private void inOrder(List<E> walk) {
		if(leftChild != null){ 
			leftChild.inOrder(walk);
		}
		walk.add(value);
		if(rightChild != null){ 
			rightChild.inOrder(walk);
		}
	}

}
