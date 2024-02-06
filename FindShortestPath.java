/**
 * FindSorttestPath
 * Algorithm to find the shortest path from the start of the dungeon to the end, then retracing our steps
 *
 * @author Jonathan Peters
 * @version 1.0, 21/03/23
 */

import java.io.*;
public class FindShortestPath {

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new IOException("No file provided"); //Can change the type of exception
            }
            
            String dungeonFileName = args[0]; //Getting the file name of the dungeon
            Dungeon dungeon = new Dungeon(dungeonFileName); //Creating a dungeon object
            int d = 0; //For distance later in the code
            DLPriorityQueue<Hexagon> prioQ = new DLPriorityQueue<Hexagon>(); //Creating an empty prioQ of type hexagon

            dungeon.getStart().setDistanceToStart(1);
            prioQ.add(dungeon.getStart(), 0); //Enqueuing the starting tile with a priority of 0
            dungeon.getStart().markEnqueued(); //Setting the tile as enqueued into the prio q

            Hexagon lowest; //Making a temp variable to hold the smallest hexagon in the prioqueue
            boolean found = false;
            boolean modified = false;
            boolean dragonPresent;

            //Creating a while loop to go until the prioQ is empty or the exit is found
            while (prioQ.isEmpty() == false && found == false) { //Also need to add that I need to wait until
                lowest = prioQ.removeMin(); //Setting the removed tile from the prioQ to lowest <- here
                lowest.markDequeued(); //Marking the node as dequeued
                dragonPresent = false; //Resetting the variable to false again, assuming there is no dragon present or adjacent to lowest

                //Finding if a dragon tile is adjacent
                for (int i = 0; i < 6; i++) {
                    if (lowest.getNeighbour(i) != null)  {
                        if (lowest.getNeighbour(i).isDragon()) {
                            dragonPresent = true; //Setting the variable to true as there is a dragon in an adjacent tile
                        }
                    }
                }

                if (lowest.isExit()) { //Checking if lowest is the exit of the dungeon
                    found = true; //If it is, set false to true so when I break from the loop I know why
                    break; //Breaking from the loop
                } else if (lowest.isDragon() || dragonPresent == true) { //Checking if either the tile or an adjacent tile is a dragon
                    continue; //Continue because this cant be a part of the solution, so go to the next tile
                } 
                else {
                    //Looking at all of the tiles around lowest and checking its distance, type and whether is marked or not
                    for (int i = 0; i < 6; i++) {
                        modified = false; //Resetting the variable modified

                        //Checking if the tile is either there, useful or not previously visited
                        if (lowest.getNeighbour(i) != null && lowest.getNeighbour(i).isWall() == false && lowest.getNeighbour(i).isMarkedDequeued() == false) {
                            d = 1 + lowest.getDistanceToStart(); //Finding the distance of the new tile
                            if (lowest.getNeighbour(i).getDistanceToStart() > d) { //If the distance of neighbour to start is greater than d
                                lowest.getNeighbour(i).setDistanceToStart(d); //Giving the hexagon the proper distance from the start
                                lowest.getNeighbour(i).setPredecessor(lowest); //Setting the lowest/current hexagon as the predecessor of the neighbour
                                modified = true;
                            }
                            
                            if (lowest.getNeighbour(i).isMarkedEnqueued() == true) { //If its enqueued then
                                if (modified) { //Checking if modified and therefore has the same distance as d
                                    prioQ.updatePriority(lowest.getNeighbour(i), d + lowest.getNeighbour(i).getDistanceToExit(dungeon));//Updating the tiles prio in queue
                                }
                            } 
                            else { //If its not marked as enqueued
                                lowest.getNeighbour(i).markEnqueued(); //Enqueuing the neighbour 
                                //Adding the updated neighbouring hexagon to the queue with the proper distances
                                prioQ.add(lowest.getNeighbour(i), lowest.getNeighbour(i).getDistanceToStart() + lowest.getNeighbour(i).getDistanceToExit(dungeon));
                            }
                        }  
                    }
                }
            }
            //If found is set to true then we found the exit and we can print out the distance
            //Additionally, d was most recently set to the distance to the exit chamber so we can use that for the path length
            if (found == true) {
                System.out.println("Path of length " + d + " ");
            } else { //If the queue ran out of possible nodes then no path was found
                System.out.println("No path found");
            }
        //All possible exceptions that could occur in the code
        } catch (IOException e) {
            System.out.println("No file found");
        } catch (InvalidDungeonCharacterException e) {
            System.out.println("Invalid character");
        } catch (EmptyPriorityQueueException e) {
            System.out.println("Empty priority queue");
        } catch (InvalidElementException e) {
            System.out.println("Invalid element");
        } catch (InvalidNeighbourIndexException e) {
            System.out.println("Invalid neighbour");
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception caught");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }

    }
}
