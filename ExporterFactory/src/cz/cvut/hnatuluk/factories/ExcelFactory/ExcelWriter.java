/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.factories.ExcelFactory;

import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public interface ExcelWriter {
    public void write(Workbook w);
}
