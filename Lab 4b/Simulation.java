/**
 * This class conducts the simulation for lab 4b.
 * @author Ivan Evtimov
 */
public class Simulation{
  
  public static void main(String[] args){
    
   int totalSizeOfA=0;
   int simulationRuns = 50;
    for(int k = 0; k< simulationRuns; k++){
      LineQueueArray<Person> queueA = new LineQueueArray<Person>();
      LineQueueList<Person> queueB = new LineQueueList<Person>();
      LineQueueList<Person> queueC = new LineQueueList<Person>();
      
      Person frontOfB, frontOfC;
      
      Person personToMoveFromB, personToMoveFromC;
      Person personToBeRemovedFromA;
      
      for (int i = 0; i<1000; i++){
        
        ////////////control situation at queue B//////////////////
        //1.create a person
        Person newGuyB = new Person();
        
        
        //2. add the person to queueB
        queueB.add(newGuyB);
        //System.out.println(newGuyB.getCurrentPersonID() + " has been added to B at timestep " +i);
        
        //3. get the person at front of queueB
        frontOfB = queueB.peek();
        
        //...if not empty
        if(frontOfB != null){
          
          //3.1 check if waiting time == timeToStay
          if (frontOfB.getWait() == frontOfB.getTimeToStay()){
            
            //3.1.1 if yes, move to queueA and print out its id
            personToMoveFromB = queueB.remove();
            /*System.out.println("ID " + personToMoveFromB.getCurrentPersonID()
             + " wait time: " + personToMoveFromB.getTimeToStay()+
             " is being moved from queue B to queue A at timestep "+i);
             */
            queueA.add(personToMoveFromB);
            
            //3.2 if not, indent that person's waiting time
          } else {
            frontOfB.indentWaitingTime();
          }
        }
        
        ////////////control situation at queue C//////////////////
        //1.create a person
        Person newGuyC = new Person();
        
        
        //2. add the person to queueB
        queueC.add(newGuyC);
        //System.out.println(newGuyC.getCurrentPersonID() + " has been added to C at timestep " +i);
        
        //3. get the person at front of queueB
        frontOfC = queueC.peek();
        
        //...if not empty
        if(frontOfC != null){
          
          //3.1 check if waiting time == timeToStay
          if (frontOfC.getWait() == frontOfC.getTimeToStay()){
            
            //3.1.1 if yes, move to queueA and print out its id
            personToMoveFromC = queueC.remove();
            /* System.out.println("ID " + personToMoveFromC.getCurrentPersonID()
             + " wait time: " + personToMoveFromC.getTimeToStay()+
             " is being moved from queue C to queue A at timestep "+i);
             */
            queueA.add(personToMoveFromC);
            
            //3.2 if not, indent that person's waiting time
          } else {
            frontOfC.indentWaitingTime();
          }
        }
        
        
        ///////////////////control of queueA///////////////
        if(i%2==0 && queueA.peek()!=null){
          personToBeRemovedFromA = queueA.remove();
          
          //System.out.println("Person with ID " + personToBeRemovedFromA.getCurrentPersonID() 
          //+ " has been removed from A at timestep "+i);
      }
}
    //System.out.println("///////////////////// end of simulation /////////////////////");
    System.out.println("Simulation run number " + k);
    System.out.println("At the end, QueueA has length " + queueA.size());
    totalSizeOfA+=queueA.size();
    
  }
    int avgSizeOfA = totalSizeOfA/simulationRuns;
    System.out.println("///////////////////// end of simulation /////////////////////");
    System.out.println("Number of runs: "+simulationRuns+"\nAverage size of A: " + avgSizeOfA);
    
}
      
}