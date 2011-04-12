/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.api.model;

/**
 *
 * @author Lucky
 */
public enum Visibility {

    NONE {

        @Override
        public String toString() {
            return "";
        }
    },
    PUBLIC {

        @Override
        public String toString() {
            return "+";
        }
    },
    PROTECTED {

        @Override
        public String toString() {
            return "#";
        }
    },
    PRIVATE {

        @Override
        public String toString() {
            return "-";
        }
    };
}
