package org.labs.lab8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.util.Bytes;

public class MyFirstHbaseTable {

	private static final String TABLE_NAME = "user";
	private static final String CF_DEFAULT = "personal_details";
	private static final String CF_2 = "professional_data";
	private static Configuration conf;

	public static void main(String... args) throws IOException {
		conf = HBaseConfiguration.create();
		createTable();
		putData();
		modifyData();
		countRow();
		/**
		 * Load coprocessor first
		 */
//		countRowByCoprocessor();
	}

	public static void createTable() throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf);
				Admin admin = connection.getAdmin()) {
			HTableDescriptor table = new HTableDescriptor(
					TableName.valueOf(TABLE_NAME));
			table.addFamily(new HColumnDescriptor(CF_DEFAULT)
					.setCompressionType(Algorithm.NONE));
			table.addFamily(new HColumnDescriptor(CF_2)
					.setCompressionType(Algorithm.NONE));

			System.out.print("Creating table.... ");

			if (admin.tableExists(table.getTableName())) {
				admin.disableTable(table.getTableName());
				admin.deleteTable(table.getTableName());
			}
			admin.createTable(table);

			System.out.println(" Done!");

		}
	}

	public static void putData() throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf)) {
			Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
			List<Put> list = new ArrayList<Put>();
			Put p1 = new Put(Bytes.toBytes("1"));
			p1.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("Name"),
					Bytes.toBytes("John"));
			p1.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("City"),
					Bytes.toBytes("Boston"));
			p1.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("Designation"),
					Bytes.toBytes("Manager"));
			p1.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("salary"),
					Bytes.toBytes("150000"));

			Put p2 = new Put(Bytes.toBytes("2"));
			p2.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("Name"),
					Bytes.toBytes("Mary"));
			p2.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("City"),
					Bytes.toBytes("New York"));
			p2.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("Designation"),
					Bytes.toBytes("Sr. Engineer"));
			p2.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("salary"),
					Bytes.toBytes("130000"));

			Put p3 = new Put(Bytes.toBytes("3"));
			p3.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("Name"),
					Bytes.toBytes("Bob"));
			p3.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("City"),
					Bytes.toBytes("Fremont"));
			p3.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("Designation"),
					Bytes.toBytes("Jr. Engineer"));
			p3.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("salary"),
					Bytes.toBytes("90000"));
			System.out.println("put data...");
			list.add(p1);
			list.add(p2);
			list.add(p3);
			table.put(list);
			System.out.println("put data done...");
		}

	}

	public static void modifyData() throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf)) {
			Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
			Put p1 = new Put(Bytes.toBytes("1"));
			System.out.println("modifing");
			p1.addColumn(Bytes.toBytes(CF_2), Bytes.toBytes("salary"),
					Bytes.toBytes("160000"));
			table.put(p1);
			System.out.println("modify done");
		}
	}

	public static void countRowByCoprocessor() throws IOException {
		try (AggregationClient agg = new AggregationClient(conf)) {
			Scan scan = new Scan();
			scan.addFamily(Bytes.toBytes(CF_2));
			System.out.println("countRowByCoprocessor");
			long count = agg.rowCount(TableName.valueOf(TABLE_NAME),
					new LongColumnInterpreter(), scan);
			System.out.println("row count is " + count);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void countRow() throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf)) {
			Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
			Scan scan = new Scan();
			scan.setFilter(new FirstKeyOnlyFilter());
			ResultScanner scanner = table.getScanner(scan);
			System.out.println("countRow...");
			long count = 0;
			for (Result r : scanner) {
				count += r.size();
			}
			System.out.println("row count is " + count);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
