package pl.retsuz.shell.variations.mkdir;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mkdir_Path extends CommandVariation {
    public Mkdir_Path(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());

        try {
            if(!params.contains("/")) {
                IComposite iComposite = new Composite();
                iComposite.setName(params);
                c.addElement(iComposite);
            }else{
                System.out.println("Niepoprawnie wprowadzona nazwa katalogu!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
