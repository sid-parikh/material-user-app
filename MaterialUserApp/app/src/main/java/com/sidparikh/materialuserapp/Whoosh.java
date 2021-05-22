package com.sidparikh.materialuserapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "whooshes", primaryKeys = {"team_num", "match_num"})
public class Whoosh implements Serializable {

    /**
     * This class is a blueprint for a Whoosh object, which will be the future name of what was once
     * "DeepSpace" and "PowerUp". These objects are Entities of the Room database. You can treat
     * them like rows for our purposes, though in reality they act more like tables. Each Whoosh
     * contains all of the data scouted in a single match on a single tablet. Whooshes are
     * differentiated by their team and match numbers. No two whooshes should have the same team and
     * match number. This will cause a Primary Key conflict.
     */

    @ColumnInfo(name = "team_num")
    private int team;

    @ColumnInfo(name = "match_num")
    private int match;

    @ColumnInfo(name = "alliance")
    private boolean alliance; // true = red; false = blue

    @ColumnInfo(name = "auto_lower_cells")
    private int autoLowerCells;

    @ColumnInfo(name = "auto_outer_cells")
    private int autoOuterCells;

    @ColumnInfo(name = "auto_inner_cells")
    private int autoInnerCells;

    @ColumnInfo(name = "auto_pickup_cells")
    private int autoPickupCells;

    @ColumnInfo(name = "teleop_lower_cells")
    private int teleopLowerCells;

    @ColumnInfo(name = "teleop_outer_cells")
    private int teleopOuterCells;

    @ColumnInfo(name = "teleop_inner_cells")
    private int teleopInnerCells;

    @ColumnInfo(name = "rotation_control")
    private boolean rotationControl;

    @ColumnInfo(name = "position_control")
    private boolean positionControl;

    @ColumnInfo(name = "bottom_port_endgame")
    private int ePowerCell1;

    @ColumnInfo(name = "outer_port_endgame")
    private int ePowerCell2;

    @ColumnInfo(name = "inner_port_endgame")
    private int ePowerCell3;

    @ColumnInfo(name = "endgame_outcome")
    private String endgameOutcome;

    /**
     * Field shooting locations
     * <p>
     * "BS" for behind shield; "FS" for front shield; "BW" for behind wheel; "FW" for front wheel;
     * "BL" for behind initiation line; "FL" for front initiation line; "SZ" for safe zone;
     */
    @ColumnInfo(name = "locations")
    private String locations;

    @ColumnInfo(name = "defenseSecs")
    private int defenseSecs;

    @ColumnInfo(name = "climbSecs")
    private int climbSecs;

    public Whoosh(int team, int match, boolean alliance) {
        this.team = team;
        this.match = match;
        this.alliance = alliance;
    }

    public Whoosh() {

    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public boolean isAlliance() {
        return alliance;
    }

    public void setAlliance(boolean alliance) {
        this.alliance = alliance;
    }

    public int getAutoLowerCells() {
        return autoLowerCells;
    }

    public void setAutoLowerCells(int autoLowerCells) {
        this.autoLowerCells = autoLowerCells;
    }

    public int getAutoOuterCells() {
        return autoOuterCells;
    }

    public void setAutoOuterCells(int autoOuterCells) {
        this.autoOuterCells = autoOuterCells;
    }

    public int getAutoInnerCells() {
        return autoInnerCells;
    }

    public void setAutoInnerCells(int autoInnerCells) {
        this.autoInnerCells = autoInnerCells;
    }

    public int getAutoPickupCells() {
        return autoPickupCells;
    }

    public void setAutoPickupCells(int autoPickupCells) {
        this.autoPickupCells = autoPickupCells;
    }

    public int getTeleopLowerCells() {
        return teleopLowerCells;
    }

    public void setTeleopLowerCells(int teleopLowerCells) {
        this.teleopLowerCells = teleopLowerCells;
    }

    public int getTeleopOuterCells() {
        return teleopOuterCells;
    }

    public void setTeleopOuterCells(int teleopOuterCells) {
        this.teleopOuterCells = teleopOuterCells;
    }

    public int getTeleopInnerCells() {
        return teleopInnerCells;
    }

    public void setTeleopInnerCells(int teleopInnerCells) {
        this.teleopInnerCells = teleopInnerCells;
    }

    public boolean isRotationControl() {
        return rotationControl;
    }

    public void setRotationControl(boolean rotationControl) {
        this.rotationControl = rotationControl;
    }

    public boolean isPositionControl() {
        return positionControl;
    }

    public void setPositionControl(boolean positionControl) {
        this.positionControl = positionControl;
    }

    public int getePowerCell1() {
        return ePowerCell1;
    }

    public void setePowerCell1(int ePowerCell1) {
        this.ePowerCell1 = ePowerCell1;
    }

    public int getePowerCell2() {
        return ePowerCell2;
    }

    public void setePowerCell2(int ePowerCell2) {
        this.ePowerCell2 = ePowerCell2;
    }

    public int getePowerCell3() {
        return ePowerCell3;
    }

    public void setePowerCell3(int ePowerCell3) {
        this.ePowerCell3 = ePowerCell3;
    }

    public String getEndgameOutcome() {
        return endgameOutcome;
    }

    public void setEndgameOutcome(String endgameOutcome) {
        this.endgameOutcome = endgameOutcome;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public int getDefenseSecs() {
        return defenseSecs;
    }

    public void setDefenseSecs(int defenseSecs) {
        this.defenseSecs = defenseSecs;
    }

    public int getClimbSecs() {
        return climbSecs;
    }

    public void setClimbSecs(int climbSecs) {
        this.climbSecs = climbSecs;
    }

    @NonNull
    @Override
    public String toString() {
        return team
                + "," + match
                + "," + (alliance ? "r" : "b")
                + "," + autoLowerCells
                + "," + autoOuterCells
                + "," + autoInnerCells
                + "," + autoPickupCells
                + "," + teleopLowerCells
                + "," + teleopOuterCells
                + "," + teleopInnerCells
                + "," + (rotationControl ? "y" : "n")
                + "," + (positionControl ? "y" : "n")
                + "," + ePowerCell1
                + "," + ePowerCell2
                + "," + ePowerCell3
                + "," + locations
                + "," + endgameOutcome
                + "," + defenseSecs
                + "," + climbSecs
                + "|";
    }
}
