package ph.inv.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ph.inv.annotation.Searchable;
import ph.inv.dao.InventoryDao;
import ph.inv.dao.impl.InventoryDaoImpl;
import ph.inv.entity.BaseEntity;
import ph.inv.entity.Inventory;

public class QueryBuilder {

	private QueryTable queryTable;
	
	public QueryBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String [] args) {
		/*InventoryDao inventoryDao = new InventoryDaoImpl();
		
		inventoryDao.findBySearch("2", "date");*/
		
		QueryBuilder queryBuilder = new QueryBuilder();

		Map<String,List<String>> searchableFields = new LinkedHashMap<String,List<String>>();
		
		QueryTable table = queryBuilder.traverseSearchableFields(Inventory.class, Searchable.class);
		
		System.out.println(table.getTableName() + " : " + table.getNestedTables().size());
		for(QueryTable t : table.getNestedTables()) {
			System.out.println(t.getTableName());
			if(t.getNestedTables() != null) {
				Iterator<QueryTable> i = t.getNestedTables().iterator();
				while(i.hasNext()) {
					System.out.println(i.next().getTableName());
				}
			}
		}
	}
	
	protected QueryTable traverseSearchableFields(Class clazz, Class annotation) {
		//System.out.println("Clazz: " + clazz.getName() + " : " + clazz.getSimpleName());
		Field [] declaredFields = clazz.getDeclaredFields();
		
		QueryTable queryTable = new QueryTable();
		queryTable.setTableName(clazz.getSimpleName());
		queryTable.setParentClass(null);
		List<String> columns = new LinkedList<String>();
		List<String> columnTypes = new LinkedList<String>();
		
		for(Field field : declaredFields) {
			if(field.isAnnotationPresent(annotation)) {
				//Field is an entity
				if(BaseEntity.class.isAssignableFrom(field.getType())) {
					try {
						Class<?> clasz = Class.forName(field.getType().getCanonicalName());
						deepSearch(clasz, annotation, clazz, queryTable);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					//Normal field
					columns.add(field.getName());
					columnTypes.add(field.getType().getName());
				}
			}
		}
		queryTable.setColumnName(columns);
		queryTable.setColumnType(columnTypes);

		
		return queryTable;
	}

	protected void deepSearch(Class clazz, Class annotation, Class parentClass, QueryTable queryTable) {
		//System.out.println("Clazz: " + clazz.getName() + " : " + clazz.getSimpleName());
		Field [] declaredFields = clazz.getDeclaredFields();
		List<QueryTable> queryTables = new LinkedList<QueryTable>();
		List<String> columns = new LinkedList<String>();
		List<String> columnTypes = new LinkedList<String>();
		QueryTable table = new QueryTable();
		
		if(queryTable.getNestedTables() != null) {
			Iterator<QueryTable> i = queryTable.getNestedTables().iterator();
			System.out.println(queryTable.getNestedTables() + " Size: " + queryTable.getNestedTables().size());
	/*		while(i.hasNext()) {
				System.out.println(i.next().getTableName());
			}*/
		} else {
			table.setParentClass(parentClass.getSimpleName());
			table.setTableName(clazz.getSimpleName());
			queryTables.add(table);
			queryTable.setNestedTables(queryTables);
		}
		
		for(Field field : declaredFields) {
			if(field.isAnnotationPresent(annotation)) {
				//Field is an entity
				if(BaseEntity.class.isAssignableFrom(field.getType())) {
					try {
						Class<?> clasz = Class.forName(field.getType().getCanonicalName());
						deepSearch(clasz, annotation, clazz, queryTable);
						System.out.println("	Field: " + field.getName() + " : " + field.getType().getCanonicalName());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					//Normal field
					columns.add(field.getName());
					columnTypes.add(field.getType().getName());
				}
			}
		}
		table.setColumnName(columns);
		table.setColumnType(columnTypes);
	}
	
	public QueryTable getQueryTable() {
		return queryTable;
	}

	public void setQueryTable(QueryTable queryTable) {
		this.queryTable = queryTable;
	}
}
