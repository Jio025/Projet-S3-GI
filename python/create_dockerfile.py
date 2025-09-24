import os
import random

dockerOS= ["Debian", "Alpine", "Ubuntu", "Arch", "CentOS", "Fedora"]
packages = [
    "bash","coreutils","grep","sed","awk","findutils","gzip","bzip2","xz","tar",
    "curl","wget","git","vim","nano","emacs","htop","tmux","screen","zsh",
    "fish","openssh","rsync","nmap","netcat","tcpdump","iptables","ufw","nginx","apache2",
    "lighttpd","postgresql","mysql-server","mariadb-server","sqlite3","redis","mongodb","docker","podman","kubectl",
    "ansible","terraform","qemu-kvm","virtualbox","wine","ffmpeg","imagemagick","gimp","inkscape","libreoffice","texlive"
]
recources = [
    "abandon","ability","able","abortion","about","above","abroad","absence","absolute","absolutely","absorb","abuse","academic",
    "accept","access","accident","accompany","accomplish","according","account","accurate","accuse","achieve","achievement","acid",
    "acknowledge","acquire","across","act","action","active","activist","activity","actor","actress","actual","actually","ad","adapt",
    "add","addition","additional","address","adequate","adjust","adjustment","administration","administrator","admire","admission",
    "admit","adolescent","adopt","adult","advance","advanced","advantage","adventure","advertising","advice","advise","adviser","advocate"
]
linux_software_paths = [
    "/bin", "/sbin", "/usr/bin", "/usr/sbin", "/usr/local/bin", "/usr/local/sbin",
    "/usr/local/games", "/usr/games", "/lib", "/lib64", "/usr/lib", "/usr/lib64",
    "/usr/local/lib", "/usr/local/lib64", "/opt", "/opt/bin", "/opt/sbin", "/opt/lib",
    "/opt/local", "/var/lib", "/var/lib/dpkg", "/var/lib/rpm", "/var/lib/apt", "/var/lib/snapd",
    "/var/cache", "/var/cache/apt", "/var/cache/dnf", "/etc", "/etc/opt", "/etc/alternatives",
    "/srv", "/srv/software", "/root/bin", "/root/.local/bin", "/home/user/bin", "/home/user/.local/bin",
    "/home/user/.local/lib", "/mnt", "/media", "/snap", "/flatpak", "/var/snap", "/var/flatpak",
    "/boot", "/usr/src", "/usr/local/src", "/usr/include", "/usr/local/include", "/var/tmp", "/tmp"
]

for j in range(10):
    target_directory = "/home/guillaume/IdeaProjects/Projet-S3-GI/python/dockerfiles"
    file_name = f"DOCKERFILE{j}"
    full_path = os.path.join(target_directory, file_name)


    randomIntOs = random.randint(0, 5)
    randomIntPackage = random.randint(0, len(packages)-1)
    randomIntRecources = random.randint(0, len(recources)-1)
    randomIntNoPackage = random.randint(1, 10)
    randomIntNoRecources = random.randint(1, 10)

    listString = []
    # appending the OS
    listString.append(f"FROM {dockerOS[randomIntOs].lower()}:latest\n")
    # appending the packages
    if randomIntOs == 1:  # Alpine
        listString.append("RUN apk update && apk add ")
    elif randomIntOs == 3:  # Arch
        listString.append("RUN pacman -Syy && pacman -S --noconfirm ")
    elif randomIntOs == 4:  # CentOS
        listString.append("RUN yum update -y && yum install -y ")
    elif randomIntOs == 5:  # Fedora
        listString.append("RUN dnf update -y && dnf install -y ")
    else:  # Debian/Ubuntu
        listString.append("RUN apt-get update -y && apt-get install -y ")

    pkgs = [packages[(randomIntPackage + i) % len(packages)] for i in range(randomIntNoPackage)]
    listString.append(" ".join(pkgs) + "\n\n")
    # appending the recources
    for i in range(randomIntNoRecources):
        listString.append("COPY ")
        listString.append(recources[(randomIntRecources + i) % len(recources)])
        listString.append(".py ")
        randomIntPath = random.randint(0, len(linux_software_paths)-1)
        listString.append(linux_software_paths[randomIntPath])
        listString.append("\n")

    finalstring = "".join(listString)
    with open(full_path,"w") as f:
        f.write(finalstring)
        f.close()