package pl.retsuz.shell.variations.mv;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;



public class Mv_Path extends CommandVariation {
    public Mv_Path(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*\\s[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {
        String[] allParams = params.split(" ");
        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite elem = c.findElementByPath(allParams[0]);
            IComposite src = elem.getParent();
            IComposite dst = c.findElementByPath(allParams[1]);
            Composite.moveElement(src, dst, elem);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
