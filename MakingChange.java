import java.util.*;
import java.util.stream.*;
class MakingChange{
		public static void main(String[] args){
			try{
				System.out.print("Enter total sum that you want to change : ");
				Scanner scanner = new Scanner(System.in);
				int global = scanner.nextInt();
				if(global<=0){
					System.out.println("Please enter the number greater than zero");
					return;
				}
				System.out.print("How many coins do you have ? ");
				int size = scanner.nextInt();
				if(size<=0){
					System.out.println("Please enter the number greater than zero");
					return;
				}
				ArrayList<Integer> listCoins = new ArrayList<>();
				for(int i=0;i<size;i++){
					System.out.print("Enter coin "+" "+(i+1)+" : " );
					int j = scanner.nextInt();
					if(j<=0){
						System.out.println("Please enter the number greater than zero");
						return;
					}
					listCoins.add(j);				
				}
				int index=0;
				List<Map<Integer,Integer>> main = new ArrayList<>();
				for(int i : listCoins){
					List<Map<Integer,Integer>> list = makeChange(global,listCoins,index,new ArrayList<>(),global);
					main.addAll(list);
					index++;
				}
			List<Map<Integer, Integer>> withouDuplicates = main.stream()
                                                      .distinct()
                                                      .collect(Collectors.toList());
             System.out.println("There are "+withouDuplicates.size()+" possibilities.");
             System.out.println(withouDuplicates);
			for(Map<Integer,Integer> local_map : withouDuplicates){
				for (Map.Entry<Integer,Integer> entry : local_map.entrySet())  
					{
						for(int i=0;i<entry.getValue();i++){
								System.out.print(entry.getKey()+" ");
						}
					}
					System.out.println();
			}	
			}catch(NumberFormatException e1){
				System.out.println("Sorry ! You have entered invalid number, please proceed again.");
				return;
			}catch(Exception e2){
				System.out.println("Sorry ! You have entered invalid number, please proceed again.");
				return;
			}				
		}
		private static List<Map<Integer,Integer>> makeChange(int total, ArrayList<Integer> coins, int index,List<Map<Integer,Integer>> list,int global){
			if(index == coins.size()){
					return list;
			}else{
			
				int current_coin = coins.get(index);
				int div = total/current_coin;
				while(div!=0){
					int half_sum = current_coin*div;
					int local_reminder = total-half_sum;
					if(local_reminder==0){
							if(global==div*current_coin){
							Map<Integer,Integer> map = new HashMap<>();
							map.put(current_coin,div);
							list.add(map);
						}
					}else{
						for(int i : coins){
							if(i!=current_coin){
									if(i==local_reminder){
										Map<Integer,Integer> map_existing_coin = new HashMap<>();
										map_existing_coin.put(current_coin,div);
										map_existing_coin.put(i,1);
										list.add(map_existing_coin);					
									}else{
										if(local_reminder<i){
									
										}else{
										
											int local_index = 0;
											for(int local_i : coins){
												if(local_i!=current_coin){
													ArrayList<Integer> local_coins = new ArrayList<>(coins);
													local_coins.remove(new Integer(current_coin));
										
													List<Map<Integer,Integer>> local_list = makeChange(local_reminder,local_coins,0,new ArrayList<>(),local_reminder);	
											
													
													for(Map<Integer,Integer> local_map : local_list){
																int child_sum = 0;
																	Map<Integer,Integer> map_existing_coin = new HashMap<>();
																	map_existing_coin.put(current_coin,div);
																for (Map.Entry<Integer,Integer> entry : local_map.entrySet())  
																	{
																		Integer k = entry.getKey();
																		Integer v = entry.getValue();
																		map_existing_coin.put(k,v);
																		child_sum += (k*v);
																	}
																	if((child_sum)+(current_coin*div)==global){
																			list.add(map_existing_coin);
																	}
													}
												}
												local_index++;
											}
											
										}
									}
							}
						}
					}
					div--;
				}
			
				return list;
			}
		}
}
