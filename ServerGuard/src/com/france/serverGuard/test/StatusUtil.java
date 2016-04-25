package com.france.serverGuard.test;

import java.net.InetAddress;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

public class StatusUtil {

	/**
	 * @param args
	 */
//	public static void main(String[] args) throws SigarException {
//		 try {
//			 getOs();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	public static String getOs() throws SigarException{
		 Sigar sigar = new Sigar();  
		 StringBuilder stringBuilder=new StringBuilder();
	        // 取到当前操作系统的名称    
	        String hostname = "";  
	        try {  
	            hostname = InetAddress.getLocalHost().getHostName();  
	        } catch (Exception e) {  
	            hostname = sigar.getNetInfo().getHostName();  
	        }  
	        stringBuilder.append("主机名："+hostname);
//	        System.out.println(hostname);  
//	  
//	        // 获取当前操作系统的信息    
	        OperatingSystem operatingSystem = OperatingSystem.getInstance();  
//	        System.out.println("operatingSystem arch：" + operatingSystem.getArch());// 操作系统内核类型如： 386、486、586等x86  
//	        System.out.println("operatingSystem cpuEndian：" + operatingSystem.getCpuEndian());//  
//	        System.out.println("operatingSystem dataModel：" + operatingSystem.getDataModel());//  
//	        System.out.println("operatingSystem description：" + operatingSystem.getDescription());// 系统描述  
	        stringBuilder.append(" 操作系统:"+operatingSystem.getDescription());
//	        System.out.println("operatingSystem machine：" + operatingSystem.getMachine());//    
//	        System.out.println("operatingSystem name：" + operatingSystem.getName());// 操作系统类型  
//	        System.out.println("operatingSystem patchLevel：" + operatingSystem.getPatchLevel());// 操作系统补丁级别  
//	        System.out.println("operatingSystem vendor：" + operatingSystem.getVendor());// 操作系统供应商   
//	        System.out.println("operatingSystem vendorCodeName：" + operatingSystem.getVendorCodeName());// 供应商编码名  
//	        System.out.println("operatingSystem vendorName：" + operatingSystem.getVendorName());// 操作系统供应商名称   
//	        System.out.println("operatingSystem vendorVersion：" + operatingSystem.getVendorVersion());// 操作系统供应商版本    
//	        System.out.println("operatingSystem version：" + operatingSystem.getVersion());// 操作系统的版本号  
//	  
//	        // 获取当前系统进程表中的用户信息    
//	        Who whoArray [] = sigar.getWhoList();  
//	        if (whoArray != null) {  
//	            for (int i = 0; i < whoArray.length; i++) {  
//	                Who who = whoArray[i];  
//	                System.out.println("\n~~~~~~~~~" + i + "~~~~~~~~~~~~");  
//	                System.out.println("who device：" + who.getDevice());  
//	                System.out.println("who host：" + who.getHost());  
//	                System.out.println("who time：" + who.getTime());  
//	                System.out.println("who user：" + who.getUser());// 当前系统进程表中的用户名  
//	            }  
//	        }  
	        sigar.close();  
	        return stringBuilder.toString();
	}
	static void getDisk() throws SigarException{
		Sigar sigar = new Sigar();  
		  
        FileSystem [] fileSystemArray = sigar.getFileSystemList();  
        for (int i=0;i<fileSystemArray.length;i++ ) {
        	FileSystem fileSystem=fileSystemArray[i];
            System.out.println("fileSystem dirName：" + fileSystem.getDirName());//分区的盘符名称   
            System.out.println("fileSystem devName：" + fileSystem.getDevName());//分区的盘符名称   
            System.out.println("fileSystem typeName：" + fileSystem.getTypeName());// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等    
            System.out.println("fileSystem sysTypeName：" + fileSystem.getSysTypeName());//文件系统类型，比如 FAT32、NTFS  
            System.out.println("fileSystem options：" + fileSystem.getOptions());  
            System.out.println("fileSystem flags：" + fileSystem.getFlags());  
            System.out.println("fileSystem type：" + fileSystem.getType());  
              
            FileSystemUsage fileSystemUsage = null;  
              
            try {  
                fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());  
            } catch (SigarException e) {//当fileSystem.getType()为5时会出现该异常——此时文件系统类型为光驱  
                continue;  
            }  
            System.out.println("fileSystemUsage total：" + fileSystemUsage.getTotal() + "KB");// 文件系统总大小  
            System.out.println("fileSystemUsage free：" + fileSystemUsage.getFree() + "KB");// 文件系统剩余大小   
            System.out.println("fileSystemUsage used：" + fileSystemUsage.getUsed() + "KB");// 文件系统已使用大小  
            System.out.println("fileSystemUsage avail：" + fileSystemUsage.getAvail() + "KB");// 文件系统可用大小  
            System.out.println("fileSystemUsage files：" + fileSystemUsage.getFiles());  
            System.out.println("fileSystemUsage freeFiles：" + fileSystemUsage.getFreeFiles());  
            System.out.println("fileSystemUsage diskReadBytes：" + fileSystemUsage.getDiskReadBytes());  
            System.out.println("fileSystemUsage diskWriteBytes：" + fileSystemUsage.getDiskWriteBytes());  
            System.out.println("fileSystemUsage diskQueue：" + fileSystemUsage.getDiskQueue());  
            System.out.println("fileSystemUsage diskServiceTime：" + fileSystemUsage.getDiskServiceTime());  
            System.out.println("fileSystemUsage usePercent：" + fileSystemUsage.getUsePercent() * 100 + "%");// 文件系统资源的利用率    
            System.out.println("fileSystemUsage diskReads：" + fileSystemUsage.getDiskReads());  
            System.out.println("fileSystemUsage diskWrites：" + fileSystemUsage.getDiskWrites());  
            System.err.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");  
        }  
	}
	public static String getCpu() throws SigarException{
		Sigar sigar = new Sigar();  
		StringBuilder stringBuilder=new StringBuilder();
		CpuInfo infos[] = sigar.getCpuInfoList();
	       CpuPerc cpuList[] = null;
	       cpuList = sigar.getCpuPercList();
	       for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
	           stringBuilder.append("第" + (i + 1) + "块CPU信息:");
	           stringBuilder.append("CPU总的使用率:    " + CpuPerc.format(cpuList[i].getCombined())+" ////");
	       }
	       sigar.close();
	    return stringBuilder.toString();
	}
	public static String getNet() throws SigarException{
		Sigar sigar = new Sigar();  
		StringBuilder stringBuilder=new StringBuilder();   
		stringBuilder.append("ipv4:"+sigar.getFQDN());
		
//        // 当前机器的正式域名  
//        try {  
//            System.out.println(sigar.getFQDN());// 即Fully Qualified Domain Name，中文为：全称域名  ipv4
//        } catch (SigarException e) {  
//            e.printStackTrace();  
//        }  
//  
//        String[] netInterfaceList = sigar.getNetInterfaceList();  
//  
//        // 获取网络流量信息  
//        for (int i = 0; i < netInterfaceList.length; i++) {  
//            String netInterface = netInterfaceList[i];// 网络接口  
//            System.out.println("netInterface：" + netInterface);  
//            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);  
//            System.out.println("Address = " + netInterfaceConfig.getAddress());// IP地址  
//            System.out.println("Netmask = " + netInterfaceConfig.getNetmask());// 子网掩码  
//            if ((netInterfaceConfig.getFlags() & 1L) <= 0L) {// 网络装置是否正常启用  
//                System.out.println("!IFF_UP...skipping getNetInterfaceStat");  
//                continue;  
//            }  
//            NetInterfaceStat netInterfaceStat = sigar.getNetInterfaceStat(netInterface);  
//            System.out.println("netInterfaceStat rxPackets：" + netInterfaceStat.getRxPackets());// 接收的总包裹数  
//            System.out.println("netInterfaceStat txPackets：" + netInterfaceStat.getTxPackets());// 发送的总包裹数  
//            System.out.println("netInterfaceStat rxBytes：" + netInterfaceStat.getRxBytes());// 接收到的总字节数  
//            System.out.println("netInterfaceStat txBytes：" + netInterfaceStat.getTxBytes());// 发送的总字节数  
//            System.out.println("netInterfaceStat rxErrors：" + netInterfaceStat.getRxErrors());// 接收到的错误包数  
//            System.out.println("netInterfaceStat txErrors：" + netInterfaceStat.getTxErrors());// 发送数据包时的错误数  
//            System.out.println("netInterfaceStat rxDropped：" + netInterfaceStat.getRxDropped());// 接收时丢弃的包数  
//            System.out.println("netInterfaceStat txDropped：" + netInterfaceStat.getTxDropped());// 发送时丢弃的包数  
//            System.out.println("netInterfaceStat rxOverruns：" + netInterfaceStat.getRxOverruns());  
//            System.out.println("netInterfaceStat txOverruns：" + netInterfaceStat.getTxOverruns());  
//            System.out.println("netInterfaceStat rxFrame：" + netInterfaceStat.getRxFrame());  
//            System.out.println("netInterfaceStat txCollisions：" + netInterfaceStat.getTxCollisions());  
//            System.out.println("netInterfaceStat txCarrier：" + netInterfaceStat.getTxCarrier());  
//            System.out.println("netInterfaceStat speed：" + netInterfaceStat.getSpeed());  
//        }  
//  
//        // 一些其它的信息  
//        for (int i = 0; i < netInterfaceList.length; i++) {  
//            String netInterface = netInterfaceList[i];// 网络接口  
//            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);  
//            if (NetFlags.LOOPBACK_ADDRESS.equals(netInterfaceConfig.getAddress())  
//                || (netInterfaceConfig.getFlags() & NetFlags.IFF_LOOPBACK) != 0  
//                || NetFlags.NULL_HWADDR.equals(netInterfaceConfig.getHwaddr())) {  
//                continue;  
//            }  
//  
//            System.out.println("netInterfaceConfig name：" + netInterfaceConfig.getName());  
//            System.out.println("netInterfaceConfig hwaddr：" + netInterfaceConfig.getHwaddr());// 网卡MAC地址  
//            System.out.println("netInterfaceConfig type:" + netInterfaceConfig.getType());  
//            System.out.println("netInterfaceConfig description：" + netInterfaceConfig.getDescription());// 网卡描述信息  
//            System.out.println("netInterfaceConfig address：" + netInterfaceConfig.getAddress());// IP地址  
//            System.out.println("netInterfaceConfig destination：" + netInterfaceConfig.getDestination());  
//            System.out.println("netInterfaceConfig broadcast：" + netInterfaceConfig.getBroadcast());// 网关广播地址  
//            System.out.println("netInterfaceConfig netmask：" + netInterfaceConfig.getNetmask());// 子网掩码  
//            System.out.println("netInterfaceConfig flags：" + netInterfaceConfig.getFlags());  
//            System.out.println("netInterfaceConfig mtu：" + netInterfaceConfig.getMtu());  
//            System.out.println("netInterfaceConfig metric：" + netInterfaceConfig.getMetric());  
//        }  
        sigar.close(); 
        return stringBuilder.toString();
	}
	public static String getMem() throws SigarException{
		Sigar sigar = new Sigar();  
		StringBuilder stringBuilder=new StringBuilder();  
        // 物理内存信息    
        Mem mem = sigar.getMem();  
//        System.out.println("mem total：" + mem.getTotal() + " B");  
//        System.out.println("mem ram：" + mem.getRam() + " B");  
//        System.out.println("mem used：" + mem.getUsed() + " B");  
//        System.out.println("mem free：" + mem.getFree() + " B");  
//        System.out.println("mem actualUsed：" + mem.getActualUsed() + " B");    
//        System.out.println("mem actualFree：" + mem.getActualFree() + " B");  
//        System.out.println("mem usedPercent：" + mem.getUsedPercent() + "%");  
//        System.out.println("mem freePercent：" + mem.getFreePercent() + "%");  
        stringBuilder.append("内存使用率:"+mem.getUsedPercent() + "%")  ;
//        // 交换区信息    
//        Swap swap = sigar.getSwap();  
//        System.err.println("swap total：" + swap.getTotal() + " B");  
//        System.err.println("swap used：" + swap.getUsed() + " B");  
//        System.err.println("swap free：" + swap.getFree() + " B");  
//        System.err.println("swap pageIn：" + swap.getPageIn());  
//        System.err.println("swap pageOut：" + swap.getPageOut());  
        return stringBuilder.toString();
	}
}
