package br.com.fotosensores.helmet_detection.detection;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import com.fotosensores.helmetdetector.DetectionResult;
import com.fotosensores.helmetdetector.DetectorParameters;
import com.fotosensores.helmetdetector.HelmetDetector;

public class HelmetDetection {

	private String path;

	public HelmetDetection(String path) {
		this.path = path;
	}

	public String detector() {

		DetectorParameters parameters = new DetectorParameters();
		parameters.setCascadePath("cascade.xml");
		parameters.setScaleFactor(1.05);
		parameters.setMinNeighbors(3);
		parameters.setMinSizeX(100);
		parameters.setMinSizeY(100);

		HelmetDetector detector = new HelmetDetector(parameters);

		Mat image = Highgui.imread(path);
		DetectionResult result = detector.detect(image);

		return result.withHelmet() ? "1" : "0";
	}
}
