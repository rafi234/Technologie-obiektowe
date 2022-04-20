package iterator.car;

import obserwator.IObserver;
import stan.ArrivingCarState;
import stan.ICarState;

public class Car implements IObserver {
    private ICarState state;
    private final String name;

    public Car(String name){
        this.name = name;
    }

    public void setState(ICarState state){
        this.state = state;
    }

    public ICarState getState(){
        return this.state;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void send(boolean isFalseAlarm) {
        if(this.state.equals(new ArrivingCarState()))
            System.out.println("Pojazd już jest w akcji!");
        else
            setState(new ArrivingCarState(isFalseAlarm));

    }

    @Override
    public void handle() {
        this.state.handle(this);
    }

}
