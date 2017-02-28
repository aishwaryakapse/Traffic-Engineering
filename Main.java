
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		while (true) {
			try {
				int sizeOfMatrix = 37;
				ArrayList<Double> fsList = new ArrayList<Double>();
				int noOfFgs = 2;
				FlowGraph[] flowGraph = new FlowGraph[noOfFgs];
				Graph graph = new Graph();

				// ------------------------------------Topology-------------------------------------
				// -------------------- Sample From class example (Uncomment This for 2.1)-----------------------------------------------------
				 graph.addVertex("1", "2", "10", "1");
				 graph.addVertex("1", "3", "10", "1");
				 graph.addVertex("1", "4", "5", "10");
				 graph.addVertex("3", "2", "10", "1");
				 graph.addVertex("4", "3", "5", "1");

				// --------------------Fat Tree  (Uncomment this for 2.3)-----------------------------------------------------
				/*graph.addVertex("1", "17", "5", "1");
				graph.addVertex("2", "17", "5", "1");
				graph.addVertex("3", "18", "5", "1");
				graph.addVertex("4", "18", "5", "1");
				graph.addVertex("5", "19", "5", "1");
				graph.addVertex("6", "19", "5", "1");
				graph.addVertex("7", "20", "5", "1");
				graph.addVertex("8", "20", "5", "1");
				graph.addVertex("9", "21", "5", "1");
				graph.addVertex("10", "21", "5", "1");
				graph.addVertex("11", "22", "5", "1");
				graph.addVertex("12", "22", "5", "1");
				graph.addVertex("13", "23", "5", "1");
				graph.addVertex("14", "23", "5", "1");
				graph.addVertex("15", "24", "5", "1");
				graph.addVertex("16", "24", "5", "1");
				graph.addVertex("17", "25", "5", "1");
				graph.addVertex("17", "26", "5", "1");
				graph.addVertex("18", "25", "5", "1");
				graph.addVertex("18", "26", "5", "1");
				graph.addVertex("19", "27", "5", "1");
				graph.addVertex("19", "28", "5", "1");
				graph.addVertex("20", "27", "5", "1");
				graph.addVertex("20", "28", "5", "1");
				graph.addVertex("21", "29", "5", "1");
				graph.addVertex("21", "30", "5", "1");
				graph.addVertex("22", "29", "5", "1");
				graph.addVertex("22", "30", "5", "1");
				graph.addVertex("23", "31", "5", "1");
				graph.addVertex("23", "32", "5", "1");
				graph.addVertex("24", "31", "5", "1");
				graph.addVertex("24", "32", "5", "1");
				graph.addVertex("25", "33", "5", "1");
				graph.addVertex("25", "34", "5", "1");
				graph.addVertex("26", "35", "5", "1");
				graph.addVertex("26", "36", "5", "1");
				graph.addVertex("27", "33", "5", "1");
				graph.addVertex("27", "34", "5", "1");
				graph.addVertex("28", "35", "5", "1");
				graph.addVertex("28", "36", "5", "1");
				graph.addVertex("29", "33", "5", "1");
				graph.addVertex("29", "34", "5", "1");
				graph.addVertex("30", "35", "5", "1");
				graph.addVertex("30", "36", "5", "1");
				graph.addVertex("31", "33", "5", "1");
				graph.addVertex("31", "34", "5", "1");
				graph.addVertex("32", "35", "5", "1");
				graph.addVertex("32", "36", "5", "1");
*/
				// ---------------------------------Dcell  (Uncomment this for 2.4 ---------------------------------

				/*graph.addVertex("1", "21", "5", "1");
				graph.addVertex("2", "21", "5", "1");
				graph.addVertex("3", "21", "5", "1");
				graph.addVertex("4", "21", "5", "1");
				graph.addVertex("5", "22", "5", "1");
				graph.addVertex("6", "22", "5", "1");
				graph.addVertex("7", "22", "5", "1");
				graph.addVertex("8", "22", "5", "1");
				graph.addVertex("9", "23", "5", "1");
				graph.addVertex("10", "23", "5", "1");
				graph.addVertex("11", "23", "5", "1");
				graph.addVertex("12", "23", "5", "1");
				graph.addVertex("13", "24", "5", "1");
				graph.addVertex("14", "24", "5", "1");
				graph.addVertex("15", "24", "5", "1");
				graph.addVertex("16", "24", "5", "1");
				graph.addVertex("17", "25", "5", "1");
				graph.addVertex("18", "25", "5", "1");
				graph.addVertex("19", "25", "5", "1");
				graph.addVertex("20", "25", "5", "1");
				graph.addVertex("1", "5", "5", "1");
				graph.addVertex("2", "9", "5", "1");
				graph.addVertex("3", "13", "5", "1");
				graph.addVertex("4", "17", "5", "1");
				graph.addVertex("6", "10", "5", "1");
				graph.addVertex("7", "14", "5", "1");
				graph.addVertex("8", "18", "5", "1");
				graph.addVertex("11", "15", "5", "1");
				graph.addVertex("12", "19", "5", "1");
				graph.addVertex("16", "20", "5", "1");*/

				double[][] mainCapacityMatrix = new double[sizeOfMatrix][sizeOfMatrix];
				mainCapacityMatrix = graph.getCapacityMatrix();

				/*----Uncomment below code to see capacity matrix ----*/

				// System.out.println("Initial Capacity Matrix : ");

				/*
				 * for (int i = 1; i < sizeOfMatrix; i++) { for (int j = 1; j <
				 * sizeOfMatrix; j++) { System.out.print("\t" +
				 * mainCapacityMatrix[i][j]); } System.out.println(); }
				 */

				for (int i = 0; i < noOfFgs; i++) {
					flowGraph[i] = new FlowGraph(graph);
				}

				double fairShare = 0.001;
				double f0, f1;
				double f2, f3;
				boolean Continue = true;

				while (Continue) {

					if (flowGraph[0].sortedTunnelList.size() == 0) {
						flowGraph[0].isValid = 0;
					}

					if (flowGraph[1].sortedTunnelList.size() == 0) {
						flowGraph[1].isValid = 0;
					}

					f0 = flowGraph[0].getLimits(0, fairShare) - flowGraph[0].demandF;
					f0 = Math.round(f0 * 100);
					f0 = f0 / 100;
					f1 = flowGraph[1].getLimits(1, fairShare) - flowGraph[1].demandF;
					f1 = Math.round(f1 * 100);
					f1 = f1 / 100;

					// --------------------------------------------------------------

					while (flowGraph[0].sortedTunnelList.size() > 0
							&& flowGraph[0].getBottleneck(mainCapacityMatrix) == 0 && flowGraph[0].isValid == 1) {
						flowGraph[0].sortedTunnelList.remove(0);
						if (flowGraph[0].sortedTunnelList.size() == 0) {
							flowGraph[0].isValid = 0;
						}
					}

					while (flowGraph[1].sortedTunnelList.size() > 0
							&& flowGraph[1].getBottleneck(mainCapacityMatrix) == 0 && flowGraph[1].isValid == 1) {
						flowGraph[1].sortedTunnelList.remove(0);
						if (flowGraph[1].sortedTunnelList.size() == 0) {
							flowGraph[1].isValid = 0;
						}
					}
					// --------------------------------------------------

					if (flowGraph[0].isValid == 1 && flowGraph[1].isValid == 1) {
						// System.out.println("Active FG - FG1 FG2");

						double x1 = flowGraph[0].getBottleneck(mainCapacityMatrix);
						x1 = Math.round(x1 * 100);
						x1 = x1 / 100;
						double x2 = flowGraph[1].getBottleneck(mainCapacityMatrix);
						x2 = Math.round(x2 * 100);
						x2 = x2 / 100;
						double x3 = flowGraph[0].demandR - flowGraph[0].demandF;
						x3 = Math.round(x3 * 100);
						x3 = x3 / 100;
						double x4 = flowGraph[1].demandR - flowGraph[1].demandF;
						x4 = Math.round(x4 * 100);
						x4 = x4 / 100;

						if (compareTunnels(flowGraph[0].sortedTunnelList.get(0),
								flowGraph[1].sortedTunnelList.get(0)) == true) {

							if (((f0 + f1) >= Math.max(x1, x2)) || (f0 >= x3) || (f1 >= x4) || (f0 >= x1)
									|| (f1 >= x2)) {

								fairShare = fairShare - 0.001;

								f2 = flowGraph[0].getLimits(0, fairShare) - flowGraph[0].demandF;
								f2 = Math.round(f2 * 100);
								f2 = f2 / 100;
								f3 = flowGraph[1].getLimits(1, fairShare) - flowGraph[1].demandF;
								f3 = Math.round(f3 * 100);
								f3 = f3 / 100;
								mainCapacityMatrix = flowGraph[0].updateThePath(f2, mainCapacityMatrix);
								flowGraph[0].demandF = flowGraph[0].demandF + f2;

								mainCapacityMatrix = flowGraph[1].updateThePath(f3, mainCapacityMatrix);
								flowGraph[1].demandF = flowGraph[1].demandF + f3;

								fsList.add(fairShare);

								/*
								 * System.out.println("\t" +
								 * flowGraph[0].sortedTunnelList.get(0) + "\t" +
								 * "Shared" + "\t" + "FG0" + "\t" + f2 + "\t" +
								 * fairShare); System.out.println("\t" +
								 * flowGraph[1].sortedTunnelList.get(0) + "\t" +
								 * "Shared" + "\t" + "FG1" + "\t" + f3 + "\t" +
								 * fairShare);
								 */

								if (f0 + f1 > Math.max(x1, x2)) {
									flowGraph[0].sortedTunnelList.remove(0);
									flowGraph[1].sortedTunnelList.remove(0);
								} else if (f0 >= x1) {

									flowGraph[0].sortedTunnelList.remove(0);
									if (flowGraph[0].sortedTunnelList.size() == 0) {
										flowGraph[0].isValid = 0;
									}
								} else if (f1 >= x2) {
									flowGraph[1].sortedTunnelList.remove(0);
									if (flowGraph[1].sortedTunnelList.size() == 0) {
										flowGraph[1].isValid = 0;
									}
								}

								if (f0 >= x3) {
									flowGraph[0].isValid = 0;
								}
								if (f1 >= x4) {
									flowGraph[1].isValid = 0;
								}

								fairShare = fairShare + 0.001;
							}
						} else {

							if ((f0 > x1) || (f1 > x2) || (f0 > x3) || (f1 > x4)) {
								fairShare = fairShare - 0.001;
								f2 = flowGraph[0].getLimits(0, fairShare) - flowGraph[0].demandF;
								f2 = Math.round(f2 * 100);
								f2 = f2 / 100;
								f3 = flowGraph[1].getLimits(1, fairShare) - flowGraph[1].demandF;
								f3 = Math.round(f3 * 100);
								f3 = f3 / 100;
								mainCapacityMatrix = flowGraph[0].updateThePath(f2, mainCapacityMatrix);
								flowGraph[0].demandF = flowGraph[0].demandF + f2;
								mainCapacityMatrix = flowGraph[1].updateThePath(f3, mainCapacityMatrix);
								flowGraph[1].demandF = flowGraph[1].demandF + f3;

								fsList.add(fairShare);

								/*
								 * System.out.println("\t" +
								 * flowGraph[0].sortedTunnelList.get(0) + "\t" +
								 * "Pvt" + "\t" + "FG0" + "\t" + f2 + "\t" +
								 * fairShare); System.out.println("\t" +
								 * flowGraph[1].sortedTunnelList.get(0) + "\t" +
								 * "Pvt" + "\t" + "FG1" + "\t" + f3 + "\t" +
								 * fairShare);
								 */

								if (f0 > x1) {
									flowGraph[0].sortedTunnelList.remove(0);
								} else if (f1 > x2) {
									flowGraph[1].sortedTunnelList.remove(0);
								} else if (f0 >= x3) {
									flowGraph[0].isValid = 0;
								} else if (f1 >= x4) {
									flowGraph[1].isValid = 0;
								}

								fairShare = fairShare + 0.001;

							}
						}
					}
					// --------------------------------------------------------------------------

					else if (flowGraph[0].isValid == 1 || flowGraph[1].isValid == 1) {
						if (flowGraph[0].isValid == 1) {

							double x1 = flowGraph[0].getBottleneck(mainCapacityMatrix);
							double x2 = flowGraph[0].demandR - flowGraph[0].demandF;
							if ((f0 > x1) || (f0 > x2)) {
								fairShare = fairShare - 0.001;
								f2 = flowGraph[0].getLimits(0, fairShare) - flowGraph[0].demandF;
								f2 = Math.round(f2 * 100);
								f2 = f2 / 100;

								mainCapacityMatrix = flowGraph[0].updateThePath(f2, mainCapacityMatrix);
								flowGraph[0].demandF = flowGraph[0].demandF + f2;
								fsList.add(fairShare);

								/*
								 * System.out.println("\t" +
								 * flowGraph[0].sortedTunnelList.get(0) + "\t" +
								 * "Pvt" + "\t" + "FG0" + "\t" + f2 + "\t" +
								 * fairShare);
								 */

								if (f0 >= x1) {
									flowGraph[0].sortedTunnelList.remove(0);
								}
								if (f0 >= x2) {
									flowGraph[0].isValid = 0;
								}
								fairShare = fairShare + 0.001;
							}
						} else if (flowGraph[1].isValid == 1) {

							double x3 = flowGraph[1].getBottleneck(mainCapacityMatrix);
							double x4 = flowGraph[1].demandR - flowGraph[1].demandF;

							if ((f1 >= x3) || (f1 >= x4)) {
								fairShare = fairShare - 0.001;
								f3 = flowGraph[1].getLimits(1, fairShare) - flowGraph[1].demandF;
								f3 = Math.round(f3 * 100);
								f3 = f3 / 100;
								mainCapacityMatrix = flowGraph[1].updateThePath(f3, mainCapacityMatrix);
								fsList.add(fairShare);
								/*
								 * System.out.println("\t" +
								 * flowGraph[1].sortedTunnelList.get(0) + "\t" +
								 * "Pvt" + "\t" + "FG1" + "\t" + f3 + "\t" +
								 * fairShare);
								 */

								if (f1 >= x3) {
									flowGraph[1].sortedTunnelList.remove(0);
									if (flowGraph[1].sortedTunnelList.size() == 0) {
										flowGraph[1].isValid = 0;
									}
								}
								if (f1 >= x4) {
									flowGraph[1].isValid = 0;
								}
								fairShare = fairShare + 0.001;
								flowGraph[1].demandF = flowGraph[1].demandF + f3;
							}

						} else {
							System.out.println("Not Valid");
						}

					}
					// ----------------------------------------

					else {
						System.out.println("No Active Flow Graph");
						Continue = false;
					}

					fairShare = fairShare + 0.001;

				}

				System.out.println();
				System.out.println("Updated Capacity Matrix : ");
				System.out.println();
				for (int i = 1; i < sizeOfMatrix; i++) {
					for (int j = 1; j < sizeOfMatrix; j++) {
						System.out.print("\t" + mainCapacityMatrix[i][j]);
					}
					System.out.println();
				}

				System.out.println(fsList);

			} catch (Exception e) {
				System.out.println("Enter Valid Value. Eg : BW = 20");
			}
		}
	}

	public static boolean compareTunnels(String a0, String a1) {
		String str0 = a0;
		String str1 = a1;
		int count = 0;
		String[] str0arr = str0.split("-");
		String[] str1arr = str1.split("-");
		if (str1arr.length > str0arr.length) {
			// str1
			int i = 0;
			while (i <= str0arr.length - 2) {
				String seqn = "(.*)" + str0arr[i] + "-" + str0arr[i + 1] + "(.*)";
				if (str1.matches(seqn) == true) {
					count++;
				}
				i++;
			}
		} else {
			// str0
			int i = 0;
			while (i <= str1arr.length - 2) {
				String seqn = "(.*)" + str1arr[i] + "-" + str1arr[i + 1] + "(.*)";
				if (str0.matches(seqn) == true) {
					count++;
				}
				i++;
			}
		}

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

}
