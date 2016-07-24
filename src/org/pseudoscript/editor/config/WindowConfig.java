package org.pseudoscript.editor.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@XmlRootElement(name = "window")
public class WindowConfig {

	private static final Logger LOGGER = Logger.getLogger(WindowConfig.class.getSimpleName());
	
	private static final String XML_FILE_PATH = "WindowConfig.xml";
	
	private static final Marshaller MARSHALLER;
	private static final Unmarshaller UNMARSHALLER;
	
	private int sizeWidth;
	private int sizeHeight;
	private int locationX;
	private int locationY;
	
	static {
		JAXBContext context;
		Marshaller marshaller = null;
		Unmarshaller unmarshaller = null;
		try {
			context = JAXBContext.newInstance(WindowConfig.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException ex) {
			LOGGER.error("Failed to initialize JAXB for WindowConfig.", ex);
		}
		
		MARSHALLER = marshaller;
		UNMARSHALLER = unmarshaller;
	}
	
	public static void save(WindowConfig config) throws FileNotFoundException, IOException, JAXBException {
		OutputStream output = null;
		try {
			output = new FileOutputStream(XML_FILE_PATH);
			MARSHALLER.marshal(config, output);
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}
	
	public static WindowConfig load() throws FileNotFoundException, IOException, JAXBException {
		WindowConfig config = null;
		InputStream input = null;
		try {
			input = new FileInputStream(XML_FILE_PATH);
			config = (WindowConfig) UNMARSHALLER.unmarshal(input);
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return config;
	}
	
	@XmlElement(name = "sizeWidth", defaultValue = "800")
	public int getSizeWidth() {
		return sizeWidth;
	}
	public void setSizeWidth(int sizeWidth) {
		this.sizeWidth = sizeWidth;
	}
	
	@XmlElement(name = "sizeHeight", defaultValue = "600")
	public int getSizeHeight() {
		return sizeHeight;
	}
	public void setSizeHeight(int sizeHeight) {
		this.sizeHeight = sizeHeight;
	}
	
	@XmlElement(name = "locationX", defaultValue = "200")
	public int getLocationX() {
		return locationX;
	}
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	
	@XmlElement(name = "locationY", defaultValue = "100")
	public int getLocationY() {
		return locationY;
	}
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	
}
