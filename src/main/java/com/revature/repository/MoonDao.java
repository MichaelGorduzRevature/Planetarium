package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
		public List<Moon> getAllMoons() throws SQLException {

			try (Connection connection = ConnectionUtil.createConnection()) {
				String sql = "select * from moons";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				List<Moon> moons = new ArrayList<>();
				while(rs.next()) {
					Moon moon = new Moon();
					moon.setId(rs.getInt(1));
					moon.setName(rs.getString(2));
					moon.setMyPlanetId(1);
					moons.add(moon);
	
				}
				
			return moons;
	
			}
	}

	public Moon getMoonByName(String username, String moonName) {
		try (Connection connection = ConnectionUtil.createConnection()) {
          
		String sql = "select * from moons where name = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, moonName);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Moon moon = new Moon();
		moon.setId(rs.getInt(1));
		moon.setName(rs.getString(2));
		moon.setMyPlanetId(rs.getInt(1));
		return moon;
	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		 }
	}

	public Moon getMoonById(String username, int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
          
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(1));
			return moon;
	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		 }
	}

	public Moon createMoon(String username, Moon m) {
		try (Connection connection = ConnectionUtil.createConnection()) {
          
			String sql = "insert into moons values (default, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setInt(2, m.getMyPlanetId()); 
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(1));
			return moon;
	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		 }
	}

	public void deleteMoonById(int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows Affected: " + rowsAffected);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) { 
			String sql = "select * from moons where myPlanetId = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, planetId);
			ResultSet rs = statement.executeQuery();
			List<Moon> moons = new ArrayList<>();
			while(rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moon.setMyPlanetId(planetId);
				moons.add(moon);

			}
			
		return moons;

		}
	}
}
