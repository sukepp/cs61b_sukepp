package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.*;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r = 99;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b = 76;
    /**
     * units of energy to lose on a MOVE action
     */
    final static private double repEnergyMove = 0.15;
    /**
     * units of energy to gain on a STAY action
     */
    final static private double repEnergyStay = 0.20;
    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to bestow upon offspring.
     */
    private double repEnergyGiven = 0.5;
    /**
     * probability of taking a move when ample space available.
     */
    private double moveProbability = 0.50;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = (int)Math.round(63 + 96 * energy);
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= repEnergyMove;
        energy = Math.max(energy, 0);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += repEnergyStay;
        energy = Math.min(energy, 2);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Plip(babyEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> emptyNeighbors = new ArrayList<>();
        boolean anyClorus = false;
        for (Direction dir : neighbors.keySet()) {
            if (neighbors.get(dir).name().equals("empty")) {
                emptyNeighbors.add(dir);
            } else if (neighbors.get(dir).name().equals("clorus")) {
                anyClorus = true;
            }
        }
        if (emptyNeighbors.size() == 0) {
            // Rule 1
            return new Action(Action.ActionType.STAY);
        } else if (energy >= 1.0) {
            // Rule 2
            int selected = HugLifeUtils.randomInt(0, emptyNeighbors.size() - 1);
            return new Action(Action.ActionType.REPLICATE, emptyNeighbors.get(selected));
        }
        // Rule 3
        for (Direction dir : neighbors.keySet()) {
            if (anyClorus) {
                if (Math.random() < moveProbability) {
                    int selected = HugLifeUtils.randomInt(0, emptyNeighbors.size() - 1);
                    return new Action(Action.ActionType.MOVE, emptyNeighbors.get(selected));
                } else {
                    break;
                }
            }
        }
        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
