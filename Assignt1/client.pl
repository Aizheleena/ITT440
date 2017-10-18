#!/usr/bin/perl -w
# Filename : client.pl

use strict;
use Socket;

# initialize host and port
my $host = shift || '192.168.68.138';
my $port = shift || 7890;
my $server = "192.168.68.138";  # Host IP running the server

# create the socket, connect to the port
socket(SOCKET,PF_INET,SOCK_STREAM,(getprotobyname('tcp'))[2])
   or die "Can't create a socket $!\n";
connect( SOCKET, pack_sockaddr_in($port, inet_aton($server)))
   or die "Can't connect to port $port! \n";

my $line;
while ($line = <SOCKET>) {
   print "$line\n";
}
close SOCKET or die "close: $!";

#https://www.tutorialspoint.com/perl/perl_socket_programming.htm

