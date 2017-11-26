#!/bin/sh
IPT=/sbin/iptables
IFACE="ens37"
# clean up old settings
$IPT -t mangle -F
$IPT -t mangle -X
$IPT -t nat -F
$IPT -t nat -X
$IPT -F
$IPT -X
# disable forwarding
echo 0 > /proc/sys/net/ipv4/ip_forward
# set default policies
$IPT -P INPUT DROP
$IPT -P FORWARD DROP
$IPT -P OUTPUT ACCEPT
# enable loopback 
$IPT -A INPUT -i lo -j ACCEPT
$IPT -A OUTPUT -o lo -j ACCEPT
# allow established connections
$IPT -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
# block connection to a Network Interface
$IPT -A INPUT -i ens33 -s 192.168.157.128 -j DROP
# block incoming SSH from specific IP address
$IPT -A INPUT -p tcp -s 192.168.157.128/24 --dport 22 -m conntrack --ctstate NEW,ESTABLISHED -j DROP
# block all incoming HTTP
$IPT -A INPUT -p tcp --dport 80 -m conntrack --ctstate NEW,ESTABLISHED -j DROP


