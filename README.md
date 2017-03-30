# titanic-scala

# aws gdelt
-launched a (default) ec2
-download a .pem file from ec2 -keypairs on left - create/download key pair
-alter local pem file: chmod 400 /path/my-key-pair.pem
- connect thru ssh
ssh -i /path/my-key-pair.pem ec2-user@ec2-yourec2instsanceipaddress-198-51-100-1.compute-1.amazonaws.com
https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/AccessingInstancesLinux.html
ssh
aws console - iam - users - add user - make an administrator to get a key
sudo yum install aws-cli // wasn't needed
aws s3 ls s3://gdelt-open-data/
aws s3 cp s3://gdelt-open-data/events/20170328.export.csv .
