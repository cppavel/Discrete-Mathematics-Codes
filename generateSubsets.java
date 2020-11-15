  public static void generateSubsets(ArrayList<ArrayList<Integer>> subsets, ArrayList<Integer> current,
                                       int n, int k)
    {
        if (current.size() < k)
        {
            ArrayList<Integer> choice1 = new ArrayList<Integer> (current);
            ArrayList<Integer> choice2 = new ArrayList<Integer>(current);
            choice1.add(n);

            if(n-1>=0)
            {
                generateSubsets(subsets,choice1,n-1,k);
                generateSubsets(subsets,choice2,n-1,k);
            }


        }
        else if(current.size()==k)
        {
            subsets.add( current);
            return;
        }
    }
