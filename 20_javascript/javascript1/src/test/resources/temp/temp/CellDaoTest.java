package com.nixsolutions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.nixsolutions.dao.CellDao;
import com.nixsolutions.entity.Cell;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:persistence-beans-test.xml")
@Transactional
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
   TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    //TransactionDbUnitTestExecutionListener.class
    })

public class CellDaoTest {

    @Autowired
    public CellDao cellDao;

   // @Test
	public void testShouldretrieveCellById() throws Exception {
		Cell cell = cellDao.getCellById(1l);
		Assert.assertEquals(new Long(1), cell.getCellId());
		Assert.assertEquals("cell A", cell.getName());
	}
    
	@Test
	  @DirtiesContext
	  @Rollback(false)
   @DatabaseSetup("/cell/CellInitialDataSet.xml")
    @ExpectedDatabase("/cell/CellCreate.xml")
    public void testShouldCreateCell() throws Exception {
		Cell cellD = new Cell("cell D");
		cellDao.createCell(cellD);	
	}
   
   @Test
   @DirtiesContext
   @Rollback(false)
  @DatabaseSetup("/cell/CellInitialDataSet.xml")
  @ExpectedDatabase("/cell/CellDelete.xml") 
   public void testShouldDeleteCell() throws Exception {
		Cell cell = cellDao.getCellById(1l);
		cellDao.deleteCell(cell);	
	}
  
  @Test
  @DirtiesContext
  @Rollback(false)
  @DatabaseSetup("/cell/CellInitialDataSet.xml")
  @ExpectedDatabase("/cell/CellUpdate.xml")
   public void testShouldUpdateCell() throws Exception {
		Cell cell = cellDao.getCellById(3l);
		cell.setName("cell E");
		cellDao.updateCell(cell);	
	}
}