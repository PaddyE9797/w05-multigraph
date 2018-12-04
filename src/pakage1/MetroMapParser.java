package pakage1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class MetroMapParser {

    private BufferedReader fileInput;

    public static void main(String[] args)
    {

        if(args.length != 1)
        {
            usage();
            System.exit(0);
        }

        MultiGraphADT graph = new MultiGraph();

        String filename = "bostonmetro.txt";

        try
        {
            MetroMapParser mmp = new MetroMapParser(filename);
            mmp.generateGraphFromFile(graph);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        graph.allEdges();
        graph.allNodes();
    }


    private static void usage()
    {
        //prints a usage message to System.out
        //System.out.println("java package1.MetroMapParser <filename>");
        System.out.println("java package1.MetroMapParser filename");
    }

    public MetroMapParser(String filename) throws IOException
    {
        //a buffered reader reads line by line, returning null when file is done
        fileInput = new BufferedReader(new FileReader(filename));
    }

    /**
     * @effects: creates a new parser that will read from the file
     * filename unless the file does not exist. The filename should specify
     * the exact location of the file. This means it should be something like
     * /mit/$USER/6.170/package1/bostonmetro.txt
     *
     *
     * @throws java.io.IOException if there <tt>filename</tt> cannot be read
     *
     * @returns a new MetroMapParser that will parse the file filename
     */

    private class tempEdge {
        int id;
        String label;
        int originId;
        int destinationId;

        public int getId() {
            return this.id;
        }

        public String getLabel() {
            return this.label;
        }

        public int getOriginId() {
            return this.originId;
        }

        public int getDestinationId() {
            return this.destinationId;
        }

        public tempEdge (int id, String label, int originId, int destinationId) {
            this.id = id;
            this.label = label;
            this.originId = originId;
            this.destinationId = destinationId;


        }
    }

    public  void  generateGraphFromFile(MultiGraphADT mg)
            throws IOException, BadFileException
    {
        //MultiGraph mg = new MultiGraph();
        //ArrayList<Edge> mgEdges = new ArrayList<>();
        String line = fileInput.readLine();
        StringTokenizer st;
        String stationID;
        String stationName;
        String lineName;
        String outboundID, inboundID;
        int currentEdge = 0;

        ArrayList<tempEdge> tempEdges = new ArrayList<tempEdge>();

        while(line != null)
        {

            //STUDENT :
            //
            //in this loop, you must collect the information necessary to
            //construct your graph, and you must construct your graph as well.
            //how and where you do this will depend on the design of your graph.
            //



            //StringTokenizer is a java.util Class that can break a string into tokens
            // based on a specified delimiter.  The default delimiter is " \t\n\r\f" which
            // corresponds to the space character, the tab character, the newline character,
            // the carriage-return character and the form-feed character.
            st = new StringTokenizer(line);

            //We want to handle empty lines effectively, we just ignore them!
            if(!st.hasMoreTokens())
            {
                line = fileInput.readLine();
                continue;
            }

            //from the grammar, we know that the Station ID is the first token on the line
            stationID = st.nextToken();

            if(!st.hasMoreTokens())
            {
                throw new BadFileException("no station name");
            }

            //from the grammar, we know that the Station Name is the second token on the line.
            stationName = st.nextToken();

            if(!st.hasMoreTokens())
            {
                throw new BadFileException("station is on no lines");
            }


            while(st.hasMoreTokens())
            {
                lineName = st.nextToken();

                if(!st.hasMoreTokens())
                {
                    throw new BadFileException("poorly formatted line info");
                }

                outboundID = st.nextToken();

                if(!st.hasMoreTokens()) {
                    throw new BadFileException("poorly formatted adjacent stations");
                }

                inboundID = st.nextToken();

                if (Integer.parseInt(outboundID) != 0) {
                    tempEdges.add(new tempEdge(currentEdge++, lineName, Integer.parseInt(outboundID), Integer.parseInt(inboundID)));
                }

                if (Integer.parseInt(inboundID) != 0) {
                    tempEdges.add(new tempEdge(currentEdge++, lineName, Integer.parseInt(outboundID), Integer.parseInt(inboundID)));
                }
            }

            mg.addNode(Integer.parseInt(stationID), stationName);

            line = fileInput.readLine();
        }
        // for each edge in edgesTemp add edge to graph...
        for (tempEdge te : tempEdges) {
            mg.addEdge(te.getLabel(), te.getOriginId(), te.getDestinationId());
        }
    }

}