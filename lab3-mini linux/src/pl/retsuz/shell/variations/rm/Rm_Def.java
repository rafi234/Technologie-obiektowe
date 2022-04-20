package pl.retsuz.shell.variations.rm;

import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Rm_Def extends CommandVariation {
    public Rm_Def(ICommandVariation next, ICommand parent) {
        super(next,parent,"");
    }

    @Override
    public void make(String params) {
        System.out.println("Zbyt mała liczba parametrów!");
    }
}
